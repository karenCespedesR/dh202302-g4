package brawlers.repository;

import brawlers.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {


    Optional<CreditCard> findByDocTypeAndDocNum(String docType, String docNum);
}
