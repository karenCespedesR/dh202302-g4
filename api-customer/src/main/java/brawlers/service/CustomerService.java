package brawlers.service;
import brawlers.event.CreatedClientProvider;
import brawlers.model.Customer;
import brawlers.exception.CostumerNotFound;
import brawlers.exception.CustomerAlreadyExists;
import brawlers.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CreatedClientProvider createdClientProvider;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CreatedClientProvider createdClientProvider) {
        this.customerRepository = customerRepository;
        this.createdClientProvider = createdClientProvider;
    }

    public Customer createCustomer(Customer customer) throws CustomerAlreadyExists {
       if (!customerRepository.existsByDocumentNumber(customer.getDocumentNumber())) {
           Customer savedCustomer = customerRepository.save(customer);
           createdClientProvider.publish(new CreatedClientProvider.Data(customer.getDocumentType(), customer.getDocumentNumber()));
           return savedCustomer;
       }
        throw new CustomerAlreadyExists("Customer #" + customer.getDocumentNumber() + " already exists.");
    }

    public Customer modifyCustomer(Customer customer) throws CostumerNotFound {
        Optional<Customer> returnedCustomer = this.customerRepository.findById(customer.getId());
        if(returnedCustomer.isPresent()) {
            Customer customerToModify = returnedCustomer.get();
            customerToModify.setName(customer.getName());
            customerToModify.setIsActive(customer.getIsActive());
            customerToModify.setSurname(customer.getSurname());
            customerToModify.setDocumentNumber(customer.getDocumentNumber());
            customerToModify.setDocumentType(customer.getDocumentType());
            customerToModify.setGenre(customer.getGenre());
            customerToModify.setBirthdate(customer.getBirthdate());
            return this.customerRepository.save(customerToModify);
        } else {
            throw new CostumerNotFound("User not found.");
        }
    }

    public void disableCustomer(Long id) throws CostumerNotFound {
        Optional<Customer> returnedCustomer = this.customerRepository.findById(id);
        if(returnedCustomer.isPresent()) {
            Customer customerToDisable = returnedCustomer.get();
            customerToDisable.setIsActive(false);
            this.customerRepository.save(customerToDisable);
        } else {
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
