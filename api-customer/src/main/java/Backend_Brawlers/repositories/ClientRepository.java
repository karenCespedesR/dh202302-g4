package Backend_Brawlers.repositories;

import Backend_Brawlers.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {
    Client findByNumeroDocumentoAndTipoDocumento (String numeroDocumento, String tipoDocumento);

}
