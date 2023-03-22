package com.dh.apicard.controller;

import com.dh.apicard.model.CreditCard;
import com.dh.apicard.model.CreditCardMovement;
import com.dh.apicard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/{documentType}/{documentNumber}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable String documentType, @PathVariable String documentNumber) {
        return ResponseEntity.ok(creditCardService.getByDocTypeAndDocNum(documentType, documentNumber));
    }

    @PostMapping
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        return new ResponseEntity<>(this.creditCardService.createCreditCard(creditCard), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CreditCard> debitCreditCard(@RequestBody CreditCardMovement creditCardMovement) {
        return new ResponseEntity<>(this.creditCardService.debitCreditCard(creditCardMovement), HttpStatus.OK);
    }
}
