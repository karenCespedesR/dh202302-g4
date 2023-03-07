package brawlers.controller;
import brawlers.entities.Wallet;
import brawlers.exception.CustomerNotFound;
import brawlers.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) throws CustomerNotFound {
        return new ResponseEntity<>(this.walletService.createWallet(wallet), HttpStatus.CREATED);
    }

    @GetMapping("/{documentType}/{documentNumber}/{coinCode}")
    public ResponseEntity<Wallet> getWalletByDocumentTypeAndDocumentNumberAndCoinCode(@PathVariable String documentType, @PathVariable String documentNumber, @PathVariable Long coinCode) throws Exception {
        return new ResponseEntity<>(this.walletService.getWalletByDocumentTypeAndDocumentNumberAndCoinCode(documentType, documentNumber, coinCode), HttpStatus.OK);
    }

    @GetMapping("/{documentType}/{documentNumber}")
    public ResponseEntity<List<Wallet>> getWalletsByDocumentTypeAndDocumentNumber(@PathVariable String documentType, @PathVariable String documentNumber) {
        return new ResponseEntity<>(this.walletService.findByDocumentTypeAndDocumentValue(documentType, documentNumber), HttpStatus.OK);
    }

    @PutMapping("/{id}/{balance}")
    public ResponseEntity<Wallet> updateBalance(@PathVariable Long id, @PathVariable Double balance) {
        return new ResponseEntity<>(this.walletService.updateBalance(balance, id), HttpStatus.OK);
    }
}