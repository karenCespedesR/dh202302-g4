package brawlers.service;
import brawlers.entities.Customer;
import brawlers.exception.CostumerNotFound;
import brawlers.exception.CustomerAlreadyExists;
import brawlers.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) throws CustomerAlreadyExists {
       if (!customerRepository.existsByDocumentNumber(customer.getDocumentNumber())) {
           return customerRepository.save(customer);
       }
        throw new CustomerAlreadyExists("Customer #" + customer.getDocumentNumber() + " already exists.");
    }

    public Customer modifyCustomer(Customer customer) throws CostumerNotFound {
        Customer returnedCustomer = this.customerRepository.findById(customer.getId()).get();
        if(returnedCustomer!=null) {
            returnedCustomer.setName(customer.getName());
            returnedCustomer.setIsActive(customer.getIsActive());
            returnedCustomer.setSurname(customer.getSurname());
            returnedCustomer.setDocumentNumber(customer.getDocumentNumber());
            returnedCustomer.setDocumentType(customer.getDocumentType());
            returnedCustomer.setGenre(customer.getGenre());
            returnedCustomer.setBirthdate(customer.getBirthdate());
            return this.customerRepository.save(returnedCustomer);
        }else {
            throw new CostumerNotFound("User not found.");
        }
    }

    public void disableCustomer(Long id) throws CostumerNotFound {
        Customer returnedCustomer = this.customerRepository.findById(id).get();
        if(returnedCustomer!=null) {
            returnedCustomer.setIsActive(false);
            this.customerRepository.save(returnedCustomer);
        }else{
            throw new CostumerNotFound("User not found.");
        }
    }

    public Customer getCustomer(String documentNumber, String documentType) throws CostumerNotFound{
        Optional<Customer> returnedCustomer = this.customerRepository.findByDocumentNumberAndDocumentType(documentNumber, documentType);
        if (returnedCustomer.isPresent()) {
            return returnedCustomer.get();
        } else {
            throw new CostumerNotFound("User not found.");
        }
    }
}
