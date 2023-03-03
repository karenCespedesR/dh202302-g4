package brawlers.service;

import brawlers.entities.Client;
import brawlers.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient (Client client){
        return clientRepository.save(client);
    }

    public Client modifyClient (Client client){
        Client client1 = this.clientRepository.findById(client.getId()).get();

        client1.setNombre(client.getNombre());
        client1.setApellido(client.getApellido());
        client1.setGenero(client.getGenero());
        client1.setFechaNacimiento(client.getFechaNacimiento());

        return this.clientRepository.save(client1);
    }

    public void disableClient(Long id){
        Client client1 = this.clientRepository.findById(id).get();

        client1.setActivo(false);
        this.clientRepository.save(client1);
    }

    public Client getClient(String numeroDocumento, String tipoDocumento) {
        Optional<Client> clientRecuperado = this.clientRepository.findByNumeroDocumentoAndTipoDocumento(numeroDocumento, tipoDocumento);

        if( clientRecuperado.isPresent() ){
            return clientRecuperado.get();
        }else {
            return null;
        }
    }

}
