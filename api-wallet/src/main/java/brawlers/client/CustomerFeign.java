package brawlers.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;

@FeignClient(name = "api-customer")
public interface CustomerFeign {

    @GetMapping("/{tipo}/{nro}")
    Client getByTipoYNumero(@PathVariable String tipo, @PathVariable String nro);

    @Getter
    @Setter
    public class Client {
        private Long id;
        private String tipoDocumento;
        private String numeroDocumento;
        private Boolean activo = false;
        private String nombre;
        private String apellido;
        private String genero;
        private LocalDate fechaNacimiento;
    }
}
