package brawlers.entities;
import lombok.*;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentType;
    private String documentNumber;
    private Boolean isActive = false;
    private String name;
    private String surname;
    private String genre;
    private LocalDate birthdate;
}
