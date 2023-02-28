package brawlers.controller;

import brawlers.entities.Client;
import brawlers.service.ClientService;
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
        return new ResponseEntity<>(this.clientService.modifyClient(client), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> disableClient(@PathVariable long id){
        this.clientService.disableClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{tipo}/{nro}")
    public ResponseEntity<Client> getClient(@PathVariable String tipo, @PathVariable String nro){
        return new ResponseEntity<>(this.clientService.getClient(tipo, nro), HttpStatus.OK);
    }
}
