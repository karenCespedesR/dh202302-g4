package brawlers.entities;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDocumento;
    private String numeroDocumento;
    private Boolean activo = false;
    private String nombre;
    private String apellido;
    private String genero;
    private LocalDate fechaNacimiento;
}
