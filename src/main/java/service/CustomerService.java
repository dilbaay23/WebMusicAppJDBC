package service;

import entity.Customer;

/**
 * Created by Moon on 23/04/2021
 */
public interface CustomerService {
    boolean save(Customer customer);
    void retrieveAll();
    void getCustomerById(int id);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    void deleteCustomerById(int id);

    int checkCustomerForLogin(String phoneNumber, String password);
}
