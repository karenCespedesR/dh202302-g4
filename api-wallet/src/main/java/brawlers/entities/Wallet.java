package brawlers.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "wallet")
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDocumento;
    private String nroDocumento;
    private double balance;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn()
    private Moneda moneda;
}
