package brawlers.controller;

import brawlers.client.CustomerFeign;
import brawlers.entities.Wallet;
import brawlers.exception.CustomerNotFound;
import brawlers.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")

public class WalletController {
    private final WalletService walletService;


    @Autowired
    public WalletController(WalletService walletService, CustomerFeign customerFeign) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) throws CustomerNotFound {
        return new ResponseEntity<>(this.walletService.createWallet(wallet), HttpStatus.CREATED);
    }

}
