package brawlers.repositories;

import brawlers.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {
    Optional<Client> findByNumeroDocumentoAndTipoDocumento (String numeroDocumento, String tipoDocumento);

}
