package Backend_Brawlers.controller;

import Backend_Brawlers.model.Client;
import Backend_Brawlers.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return new ResponseEntity<>(this.clientService.createClient(client), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Client> modifiyClient(@RequestBody Client client){
        return new ResponseEntity<>(this.clientService.modifyClient(client), HttpStatus.??);
    }
}