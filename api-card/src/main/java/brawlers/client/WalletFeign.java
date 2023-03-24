package brawlers.client;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "api-wallet")
public interface WalletFeign {

    @GetMapping("/wallet/{documentType}/{documentNumber}/{coinCode}")
    Wallet getWalletByDocumentTypeAndDocumentNumberAndCoinCode(@PathVariable String documentType, @PathVariable String documentNumber, @PathVariable String coinCode);

    @PutMapping("/wallet/{id}/{balance}")
    void updateBalance(@PathVariable Long id, @PathVariable Double balance);

    @Getter
    @Setter
    class Wallet {
        private Long id;
        private Double balance;
    }
}

