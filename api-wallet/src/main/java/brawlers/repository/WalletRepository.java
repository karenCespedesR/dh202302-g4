package brawlers.repository;

import brawlers.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByTipoDocumentoAndNroDocumentoAndMoneda_codigoMoneda(String tipoDocumento, String nroDocumento, Long codigoMoneda);
    Optional<Wallet> findByTipoDocumentoAndNroDocumento(String tipoDocumento, String nroDocumento);
}
