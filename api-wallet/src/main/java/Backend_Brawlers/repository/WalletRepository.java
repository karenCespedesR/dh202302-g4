package Backend_Brawlers.repository;

import Backend_Brawlers.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByTipoDocumentoAndNroDocumentoAndCodigoMoneda(String tipoDocumento, String nroDocumento, Long codigoMoneda);
    Optional<Wallet> findByTipoDocumentoAndNroDocumento(String tipoDocumento, String nroDocumento);
}
