package com.dh.apicard.service;

import com.dh.apicard.client.MarginFeign;
import com.dh.apicard.client.WalletFeign;
import com.dh.apicard.controller.CreditCardController;
import com.dh.apicard.exceptions.CardException;
import com.dh.apicard.exceptions.MessageError;
import com.dh.apicard.model.CreditCard;
import com.dh.apicard.model.CreditCardMovement;
import com.dh.apicard.repository.CreditCardMovementsRepository;
import com.dh.apicard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMovementsRepository creditCardMovementsRepository;
    private final MarginFeign marginFeign;
    private final WalletFeign walletFeign;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, CreditCardMovementsRepository creditCardMovementsRepository, MarginFeign marginFeign, WalletFeign walletFeign) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardMovementsRepository = creditCardMovementsRepository;
        this.marginFeign = marginFeign;
        this.walletFeign = walletFeign;
    }

    public CreditCard findByDocTypeAndDocNum(String docType, String docNum) throws CardException {
        return creditCardRepository.findByDocTypeAndDocNum(docType, docNum).orElseThrow(() -> new CardException(MessageError.CUSTOMER_NOT_HAVE_CARD));
    }

    public String create(CreditCard creditCard) throws CardException {
        if (creditCardRepository.findByDocTypeAndDocNum(creditCard.getDocType(), creditCard.getDocNum()).isPresent()) {
            throw new CardException(MessageError.CUSTOMER_WITH_CARD);
        }
        MarginFeign.CalificationDTO calificationDTO = marginFeign.calculateCalification(creditCard.getDocType(), creditCard.getDocNum());
        BigDecimal totalMarginCard = calificationDTO.getSublimits().stream().filter(sublimit -> sublimit.getConcept().name().equals(MarginFeign.CalificationDTO.Concept.CARD)).findFirst().get().getTotalMargin();
        creditCard.setAuthorized(totalMarginCard);
        creditCard.setAvailable(totalMarginCard);
        creditCard.setConsumed(BigDecimal.ZERO);
        creditCardRepository.save(creditCard);
        return creditCard.getId();
    }

    public void debit(CreditCardMovement movement) throws CardException {
        BigDecimal amount = movement.getAmount().getValue();
        CreditCard creditCard = creditCardRepository.findByDocTypeAndDocNum(movement.getDestination().getDocType(), movement.getDestination().getDocNum()).orElseThrow(() -> new CardException(MessageError.CUSTOMER_NOT_HAVE_CARD));
        creditCard.setConsumed(creditCard.getConsumed().add(amount));
        creditCard.setAvailable(creditCard.getAvailable().subtract(amount));
        creditCardRepository.save(creditCard);
        creditCardMovementsRepository.save(movement);
    }

    public void pay(CreditCardController.PayCreditCardDto payCreditCardDto) throws CardException {
        var creditCard = creditCardRepository.findByDocTypeAndDocNum(payCreditCardDto.docType(), payCreditCardDto.docNum()).orElseThrow(() -> new CardException(MessageError.CUSTOMER_NOT_HAVE_CARD));
        var walletResult= walletFeign.getWalletByDocumentTypeAndDocumentNumberAndCoinCode(payCreditCardDto.docType(), payCreditCardDto.docNum(),creditCard.getCurrency());
        if( creditCard.getConsumed().doubleValue() > walletResult.getBalance().doubleValue() ){
            throw new CardException(MessageError.CUSTOMER_NOT_FOUNDS);
        }
        var consumed = creditCard.getConsumed();
        creditCard.setConsumed(BigDecimal.ZERO);
        creditCard.setAvailable(creditCard.getAvailable().add(consumed));
        creditCardRepository.save(creditCard);
        walletFeign.updateBalance(walletResult.getId(),walletResult.getBalance()-consumed.doubleValue());
    }
}
