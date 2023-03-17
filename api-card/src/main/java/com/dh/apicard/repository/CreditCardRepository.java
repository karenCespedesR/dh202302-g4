package com.dh.apicard.repository;

import com.dh.apicard.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
    CreditCard findByDocTypeAndDocNum(String docType, Integer docNum);
}
