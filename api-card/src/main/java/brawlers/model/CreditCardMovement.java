package brawlers.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "Movement")
public class CreditCardMovement {
    @Id
    private String id;
    private Integer creditNum;
    private String movType;
    private Amount amount;
    private LocalDateTime operationDate;
    private Destination destination;
    private String description;
    private List<PurchaseDetail> purchaseDetails;
    private Boolean state;
    private BigDecimal comission;

    @Getter
    @Setter
    public static class Amount {
        private String currency;
        private BigDecimal value;
    }

    @Getter
    @Setter
    public static class Destination {
        private String docType;
        private String docNum;
        private String socialName;
    }

    @Getter
    @Setter
    public static class PurchaseDetail {
        private String itemService;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subTotal;
    }
}
