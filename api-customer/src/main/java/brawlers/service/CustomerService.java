package brawlers.service;
import brawlers.entities.Customer;
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

    public Customer modifyCustomer(Customer customer){
        Customer returnedCustomer = this.customerRepository.findById(customer.getId()).get();
        returnedCustomer.setName(customer.getName());
        returnedCustomer.setIsActive(customer.getIsActive());
        returnedCustomer.setSurname(customer.getSurname());
        returnedCustomer.setDocumentNumber(customer.getDocumentNumber());
        returnedCustomer.setDocumentType(customer.getDocumentType());
        returnedCustomer.setGenre(customer.getGenre());
        returnedCustomer.setBirthdate(customer.getBirthdate());
        return this.customerRepository.save(returnedCustomer);
    }

    public void disableCustomer(Long id){
        Customer returnedCustomer = this.customerRepository.findById(id).get();
        returnedCustomer.setIsActive(false);
        this.customerRepository.save(returnedCustomer);
    }

    public Customer getCustomer(String documentNumber, String documentType) {
        Optional<Customer> returnedCustomer = this.customerRepository.findByDocumentNumberAndDocumentType(documentNumber, documentType);
        if (returnedCustomer.isPresent()) {
            return returnedCustomer.get();
        } else {
            return null;
        }
    }
}
