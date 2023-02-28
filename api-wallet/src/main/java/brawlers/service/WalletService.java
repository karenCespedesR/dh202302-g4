package brawlers.service;
import brawlers.entities.Wallet;
import brawlers.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Wallet> walletRecibida = walletRepository.findByTipoDocumentoAndNroDocumentoAndMoneda_codigoMoneda(tipoDocumento, nroDocumento, codigoMoneda);
        if(walletRecibida.isPresent()){
            return walletRecibida.get().getBalance();
        }else{
            throw new Exception("Wallet no encontrada");
        }
    }

    public Long getBalanceByTipoDeDocumentoAndNroDeDocumento(String tipoDocumento, String nroDocumento) throws Exception {
        Optional<Wallet> walletRecibida = walletRepository.findByTipoDocumentoAndNroDocumento(tipoDocumento, nroDocumento);
        if(walletRecibida.isPresent()){
            return walletRecibida.get().getMoneda().getCodigoMoneda();
        }else{
            throw new Exception("Wallet no encontrada");
        }
    }
}
