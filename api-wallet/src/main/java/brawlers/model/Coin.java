package brawlers.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coin")
public class Coin {
    @Id
    private String code;
    private String description;
}
