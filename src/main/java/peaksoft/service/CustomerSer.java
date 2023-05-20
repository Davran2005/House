package peaksoft.service;

import peaksoft.model.Customer;

import java.util.List;

public interface CustomerSer {
    void saveCustomer(Customer customer);
    List<Customer> getAllCustomers(String word);
    Customer getCustomerById(Long id);
    void updateCustomerById(Long id, Customer customer);
    void deleteCustomerById(Long id);
    List<Customer> searchCustomer(String word);
}
