package com.dh.apicard.service;

import com.dh.apicard.model.CreditCard;
import com.dh.apicard.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard getByDocTypeAndDocNum(String docType, Integer docNum) {
        return creditCardRepository.findByDocTypeAndDocNum(docType, docNum);
    }


}
