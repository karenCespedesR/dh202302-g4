package com.dh.apicard.repository;

import com.dh.apicard.model.CreditCardMovements;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardMovementsRepository extends MongoRepository<CreditCardMovements, String> {
}
