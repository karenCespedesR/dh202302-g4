package brawlers.client;
import brawlers.config.CustomLoadBalancerConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;

@FeignClient(name = "api-customer")
@LoadBalancerClient(name = "api-customer", configuration = CustomLoadBalancerConfiguration.class)
public interface CustomerFeign {

    @GetMapping("/customer/{documentType}/{documentNumber}")
    CustomerDTO getByDocumentTypeAndDocumentNumber(@PathVariable String documentType, @PathVariable String documentNumber);

    @Getter
    @Setter
    class CustomerDTO {
        private Long id;
        private String documentType;
        private String documentNumber;
        private Boolean isActive;
        private String name;
        private String surname;
        private String genre;
        private LocalDate birthdate;
    }
}
