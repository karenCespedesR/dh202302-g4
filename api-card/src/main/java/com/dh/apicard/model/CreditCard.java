package com.dh.apicard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Document(collection = "CreditCards")
public class CreditCard {
    @Id
    private String id;
    private Integer creditNum;
    private String docType;
    private Integer docNum;
    private String currency;
    private BigDecimal consumed;
    private BigDecimal available;
    private BigDecimal authorized;
}
