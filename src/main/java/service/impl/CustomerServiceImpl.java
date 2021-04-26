package service.impl;

import entity.Customer;
import org.mindrot.jbcrypt.BCrypt;
import repo.CustomerRepository;
import repo.impl.CustomerRepositoryImpl;
import service.CustomerService;

/**
 * Created by Moon on 23/04/2021
 */
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository= new CustomerRepositoryImpl();
    @Override
    public boolean save(Customer customer) {
        String hashedPassword = hashPassword(customer.getPassword());
        customer.setPassword(hashedPassword);
        if(customerRepository.save(customer)>0) {
            System.out.println("You Have been registered successfully");
            return true;
        }else{
            System.out.println("DB Error");
            return false;
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            System.out.println("The password does not match.");
        return false;
    }

    @Override
    public void retrieveAll() {

    }

    @Override
    public void getCustomerById(int id) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomerById(int id) {

    }

    @Override
    public int checkCustomerForLogin(String phoneNumber, String password) {
        Customer customer = customerRepository.getCustomerByPhoneNumber(phoneNumber);

        boolean checkPassword = checkPass(password, customer.getPassword());
        if(customer==null || !checkPassword){
            System.out.println("Password Or Phone Number is Wrong");
            return -1;
        }
        return customer.getId().intValue();

    }
}
