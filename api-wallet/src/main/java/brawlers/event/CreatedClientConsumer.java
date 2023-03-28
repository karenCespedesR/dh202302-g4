package brawlers.event;

import brawlers.config.RabbitMQConfig;
import brawlers.exception.CustomerNotFound;
import brawlers.model.Wallet;
import brawlers.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatedClientConsumer {
    @Autowired
    private WalletService walletService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CREATED_CLIENT)
    public void listen(CreatedClientConsumer.Data message) throws CustomerNotFound {
        Wallet wallet = new Wallet();
        wallet.setDocumentType(message.getDocType());
        wallet.setDocumentNumber(message.getDocNum());
        walletService.createWallet(wallet);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static class Data {
        private String docType;
        private String docNum;
    }
}
