package brawlers.entities;
import lombok.*;
import javax.persistence.*;

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
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
