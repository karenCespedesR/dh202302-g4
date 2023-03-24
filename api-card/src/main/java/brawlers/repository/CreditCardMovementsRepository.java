package brawlers.repository;

import brawlers.model.CreditCardMovement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardMovementsRepository extends MongoRepository<CreditCardMovement, String> {
}
