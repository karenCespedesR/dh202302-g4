package com.dh.apicard.service;

import com.dh.apicard.client.MarginFeign;
import com.dh.apicard.model.CreditCard;
import com.dh.apicard.model.CreditCardMovement;
import com.dh.apicard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final MarginFeign marginFeign;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, MarginFeign marginFeign) {
        this.creditCardRepository = creditCardRepository;
        this.marginFeign = marginFeign;
    }

    public CreditCard getByDocTypeAndDocNum(String docType, String docNum) {
        return creditCardRepository.findByDocTypeAndDocNum(docType, docNum);
    }

    public CreditCard createCreditCard(CreditCard creditCard) {
        MarginFeign.CalificationDTO calificationDTO = marginFeign.calculateCalification(creditCard.getDocType(), creditCard.getDocNum());
        BigDecimal totalMargin = calificationDTO.getSublimits().get(0).getTotalMargin();
        creditCard.setAuthorized(totalMargin);
        creditCard.setAvailable(totalMargin);
        creditCard.setConsumed(new BigDecimal(0));
        return creditCardRepository.save(creditCard);
    }

    public CreditCard debitCreditCard(CreditCardMovement movement) {
        BigDecimal amount = movement.getAmount().getValue();
        CreditCard creditCard = creditCardRepository.findByDocTypeAndDocNum(movement.getDestination().getDocType(), movement.getDestination().getDocNum());
        creditCard.setConsumed(creditCard.getConsumed().add(amount));
        creditCard.setAvailable(creditCard.getAvailable().subtract(amount));
        return creditCardRepository.save(creditCard);
    }
}
