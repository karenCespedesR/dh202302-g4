package brawlers.repositories;

import brawlers.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {
    Client findByNumeroDocumentoAndTipoDocumento (String numeroDocumento, String tipoDocumento);

}
