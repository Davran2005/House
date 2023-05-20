package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.exception.MyException;
import peaksoft.model.Customer;
import peaksoft.repository.CustomerRep;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomerRepImpl implements CustomerRep {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public List<Customer> getAllCustomers(String word) {
        if (word == null || word.isEmpty())
            return entityManager.createQuery("from Customer a", Customer.class).getResultList();
        else
            return entityManager.createQuery("select u from Customer u where u.firstName ilike :word or u.lastName ilike :word", Customer.class)
                    .setParameter("word", "%" + word + "%").setParameter("word", word)
                    .getResultList();
    }

    @Override
    public Customer getCustomerById(Long id) {
        try {
            Customer customer = entityManager.find(Customer.class, id);
            if (customer.getId().equals(id)) {
                entityManager.remove(customer);
            } else {
                throw new MyException("Customer of this"
                        + id + "was not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateCustomerById(Long id, Customer customer) {
        try {
            boolean isEmpty = false;
            for (Customer customer1 : entityManager.createQuery("from Customer a", Customer.class).getResultList()) {
                if (Objects.equals(customer1.getId(), id)) {
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                Customer customer1 = entityManager.find(Customer.class, id);
                customer1.setFirstName(customer.getFirstName());
                customer1.setLastName(customer.getLastName());
                customer1.setEmail(customer.getEmail());
                customer1.setGender(customer.getGender());
                customer1.setAge(customer.getAge());
                customer1.setPhoneNumber(customer.getPhoneNumber());
                customer1.setDataOfBirth(customer.getDataOfBirth());
                customer1.setPhoto(customer.getPhoto());
                entityManager.merge(customer1);
            } else {
                throw new MyException("Customer by ID : " + id + " not found!");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
        try {
            Customer customer = entityManager.find(Customer.class, id);
            if (customer.getId().equals(id)) {
                entityManager.remove(customer);
            } else {
                throw new MyException("Customer of this"
                        + id + "was not found");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Customer> searchCustomer(String word) {
        return entityManager.createQuery("from Customer u where u.firstName like :word", Customer.class).setParameter("word", "%" + word + "%").setParameter("word", word).getResultList();
    }

}
