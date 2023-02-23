package Backend_Brawlers.service;
import Backend_Brawlers.entities.Wallet;
import Backend_Brawlers.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet updateBalance(double balance, Long id) {
        Wallet returnedWallet = walletRepository.findById(id).get();
        returnedWallet.setBalance(balance);
        return walletRepository.save(returnedWallet);
    }

    public Wallet getWallet(double balance, Long id) {
        Wallet returnedWallet = walletRepository.findById(id).get();
        returnedWallet.setBalance(balance);
        return walletRepository.save(returnedWallet);
    }
}
