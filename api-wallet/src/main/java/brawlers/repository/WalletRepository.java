package brawlers.repository;
import brawlers.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    //@Query("FROM Wallet AS w WHERE w.documentType = ?1 AND w.documentNumber = ?2 AND w.coin.code = ?3")
    Optional<Wallet> findByDocumentTypeAndDocumentNumberAndCoin_Code(String documentType, String documentNumber, String coinCode);
    List<Wallet> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
