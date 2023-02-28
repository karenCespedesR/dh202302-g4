package brawlers.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "money")
public class Moneda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoMoneda;
    private String descripcion;
}
