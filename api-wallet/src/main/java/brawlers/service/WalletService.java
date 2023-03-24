package brawlers.service;
import brawlers.client.CustomerFeign;
import brawlers.model.Wallet;
import brawlers.exception.CustomerNotFound;
import brawlers.exception.WalletNotFound;
import brawlers.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final CustomerFeign customerFeign;

    @Autowired
    public WalletService(WalletRepository walletRepository, CustomerFeign customerFeign) {
        this.walletRepository = walletRepository;
        this.customerFeign = customerFeign;
    }

    public Wallet createWallet(Wallet wallet) throws CustomerNotFound {
        CustomerFeign.CustomerDTO returnedCustomer = customerFeign.getByDocumentTypeAndDocumentNumber(wallet.getDocumentType(), wallet.getDocumentNumber());
        if (returnedCustomer != null){
            return walletRepository.save(wallet);
        } else {
            throw new CustomerNotFound("Customer not found.");
        }
    }

    public Wallet updateBalance(Long id, BigDecimal balance) throws WalletNotFound {
        Optional<Wallet> returnedWallet = walletRepository.findById(id);
        if(returnedWallet.isPresent()) {
            Wallet actualWallet = returnedWallet.get();
            actualWallet.setBalance(balance);
            walletRepository.save(actualWallet);
            return actualWallet;
        }else{
            throw new WalletNotFound("Wallet not found.");
        }
    }

    public Wallet getWalletByDocumentTypeAndDocumentNumberAndCoinCode(String documentType, String documentNumber, String coinCode) throws WalletNotFound {
        Optional<Wallet> walletRecibida = walletRepository.findByDocumentTypeAndDocumentNumberAndCoin_Code(documentType, documentNumber, coinCode);
        if (walletRecibida.isPresent()) {
            return walletRecibida.get();
        } else {
            throw new WalletNotFound("Wallet not found.");
        }
    }

    public List<Wallet> findByDocumentTypeAndDocumentValue(String documentType, String document) throws WalletNotFound {
        List<Wallet> returnedWallet = walletRepository.findByDocumentTypeAndDocumentNumber(documentType, document);
        if(returnedWallet!=null) {
            return returnedWallet;
        }else{
            throw new WalletNotFound("Wallet not found.");
        }
    }
}
