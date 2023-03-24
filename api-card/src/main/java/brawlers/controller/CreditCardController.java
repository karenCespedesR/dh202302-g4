package brawlers.controller;

import brawlers.exceptions.CardException;
import brawlers.model.CreditCard;
import brawlers.model.CreditCardMovement;
import brawlers.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/{documentType}/{documentNumber}")
    public CreditCard findByDocTypeAndDocNum(@PathVariable String documentType, @PathVariable String documentNumber) throws CardException {
        return creditCardService.findByDocTypeAndDocNum(documentType, documentNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody CreditCard creditCard) throws CardException {
        return this.creditCardService.create(creditCard);
    }


    @PutMapping("/debit")
    public void debit(@RequestBody CreditCardMovement creditCardMovement) throws CardException {
        this.creditCardService.debit(creditCardMovement);
    }

    @PutMapping("/pay")
    public void pay(@RequestBody @Valid PayCreditCardDto creditCard) throws CardException {
        this.creditCardService.pay(creditCard);
    }

    public record PayCreditCardDto(
            @NotNull
            Integer creditNum,
            @NotNull
            String docType,
            @NotNull
            String docNum
    ) {
    }


}
