package brawlers.controller;
import brawlers.model.Customer;
import brawlers.exception.CostumerNotFound;
import brawlers.exception.CustomerAlreadyExists;
import brawlers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws CustomerAlreadyExists {
        return new ResponseEntity<>(this.customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> modifyCustomer(@RequestBody Customer customer) throws CostumerNotFound {
        return new ResponseEntity<>(this.customerService.modifyCustomer(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> disableCustomer(@PathVariable long id) throws CostumerNotFound {
        this.customerService.disableCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{documentType}/{documentNumber}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String documentType, @PathVariable String documentNumber) throws CostumerNotFound {
        return new ResponseEntity<>(this.customerService.getCustomer(documentNumber, documentType), HttpStatus.OK);
    }
}
