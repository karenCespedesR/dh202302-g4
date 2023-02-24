package Backend_Brawlers.service;
import Backend_Brawlers.entities.Wallet;
import Backend_Brawlers.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Double getBalanceByTipoDeDocumentoAndNroDeDocumentoAndCodigoDeMoneda(String tipoDocumento, String nroDocumento, Long codigoMoneda) throws Exception {
        Optional<Wallet> walletRecibida = walletRepository.findByTipoDocumentoAndNroDocumentoAndCodigoMoneda(tipoDocumento, nroDocumento, codigoMoneda);
        if(walletRecibida.isPresent()){
            return walletRecibida.get().getBalance();
        }else{
            throw new Exception("Wallet no encontrada");
        }
    }

    public List<Long> getBalanceByTipoDeDocumentoAndNroDeDocumento(String tipoDocumento, String nroDocumento) throws Exception {
        Optional<Wallet> walletRecibida = walletRepository.findByTipoDocumentoAndNroDocumento(tipoDocumento, nroDocumento);
        if(walletRecibida.isPresent()){
            return walletRecibida.get().getCodigoMoneda();
        }else{
            throw new Exception("Wallet no encontrada");
        }
    }
}
