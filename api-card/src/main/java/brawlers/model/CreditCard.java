package brawlers.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Document(collection = "CreditCard")
public class CreditCard {
    @Id
    private String id;
    @NotNull
    private Integer creditNum;
    @NotNull
    private String docType;
    @NotNull
    private String docNum;
    @NotNull
    private String currency;

    @Min(0)
    private BigDecimal consumed;
    @Min(0)
    private BigDecimal available;
    @Min(0)
    private BigDecimal authorized;
}
