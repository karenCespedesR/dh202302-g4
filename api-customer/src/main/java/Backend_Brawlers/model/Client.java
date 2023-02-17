package Backend_Brawlers.model;

import lombok.Data;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String genero;
    private Date fechaNacimiento;
    private Boolean activo = true;

}
