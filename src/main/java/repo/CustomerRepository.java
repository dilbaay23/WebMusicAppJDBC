package repo;

import entity.Customer;

import java.util.List;

/**
 * Created by Moon on 23/04/2021
 */
public interface CustomerRepository {
    int save(Customer customer);
    List<Customer> retrieveAll();
    Customer getCustomerById(int id);
    Customer getCustomerByPhoneNumber(String phoneNumber);
    int updateCustomer(Customer customer);
    int deleteCustomer(Customer customer);
    int deleteCustomerById(int id);
}
