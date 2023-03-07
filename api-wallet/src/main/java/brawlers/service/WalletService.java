package brawlers.service;
import brawlers.client.CustomerFeign;
import brawlers.entities.Wallet;
import brawlers.exception.CustomerNotFound;
import brawlers.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Wallet updateBalance(Double balance, Long id) {
        Wallet returnedWallet = walletRepository.findById(id).get();
        returnedWallet.setBalance(balance);
        return walletRepository.save(returnedWallet);
    }

    public Wallet getWalletByDocumentTypeAndDocumentNumberAndCoinCode(String documentType, String documentNumber, Long coinCode) throws Exception {
        Optional<Wallet> walletRecibida = walletRepository.findByDocumentTypeAndDocumentNumberAndCoin_Code(documentType, documentNumber, coinCode);
        if (walletRecibida.isPresent()) {
            return walletRecibida.get();
        } else {
            throw new Exception("Wallet not found.");
        }
    }

    public List<Wallet> findByDocumentTypeAndDocumentValue(String documentType, String document) {
        return walletRepository.findByDocumentTypeAndDocumentNumber(documentType, document);
    }
}
