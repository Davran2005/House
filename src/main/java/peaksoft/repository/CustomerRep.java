package peaksoft.repository;

public interface CostumerRep {
    void saveCustomer(Costomer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void updateCustomerById(Long id, Customer customer);
    void deleteCustomerById(Long id);
}
