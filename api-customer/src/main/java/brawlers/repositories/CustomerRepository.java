package brawlers.repositories;
import brawlers.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {
    Optional<Customer> findByDocumentNumberAndDocumentType(String documentNumber, String documentType);
    boolean existsByDocumentNumber(String documentNumber);
}
