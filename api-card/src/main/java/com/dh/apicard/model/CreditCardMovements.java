package com.dh.apicard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "Movements")
public class CreditCardMovements {
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
    @Builder
    static class Amount {
        private String currency;
        private BigDecimal value;
    }

    @Getter
    @Setter
    @Builder
    static class Destination {
        private String docType;
        private Integer docNum;
        private String socialName;
    }

    @Getter
    @Setter
    @Builder
    static class PurchaseDetail {
        private String itemService;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal subTotal;
    }
}
