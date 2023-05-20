package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Customer;
import peaksoft.repository.CustomerRep;
import peaksoft.service.CustomerSer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerSerImpl implements CustomerSer {
    private final CustomerRep customerRep;

    @Override
    public void saveCustomer(Customer customer) {
        customerRep.saveCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers(String word) {
        return customerRep.getAllCustomers(word);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRep.getCustomerById(id);
    }

    @Override
    public void updateCustomerById(Long id, Customer customer) {
        customerRep.updateCustomerById(id, customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRep.deleteCustomerById(id);
    }

    @Override
    public List<Customer> searchCustomer(String word) {
        return customerRep.searchCustomer(word);
    }
}
