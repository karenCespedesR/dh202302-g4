package brawlers.model;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentType;
    private String documentNumber;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
