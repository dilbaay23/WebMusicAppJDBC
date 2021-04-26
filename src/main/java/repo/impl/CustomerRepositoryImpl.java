package repo.impl;

import entity.Customer;
import repo.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 23/04/2021
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    
    @Override
    public int save(Customer customer) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "INSERT INTO javadevt_genk_08.Customer (firstName, lastName, birthDate, phoneNumber, address, city, country, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDate(3, Date.valueOf(customer.getBirthDate()));
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getCountry());
            ps.setString(8, customer.getPassword());
            int result= ps.executeUpdate();

            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public List<Customer> retrieveAll() {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Customer ");

            ResultSet rs= ps.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while(rs.next()){
                customers.add(new Customer(rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getDate("birthDate").toLocalDate(),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("country")));
            }
            return customers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public Customer getCustomerById(int id) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Customer WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            Customer customer = null;

            while(rs.next()){
                customer= new Customer(rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getDate("birthDate").toLocalDate(),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("country"));
            }
            return customer;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }


    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Customer WHERE phoneNumber=?");
            ps.setString(1,phoneNumber);
            ResultSet rs = ps.executeQuery();
            Customer customer = null;

            while(rs.next()){
                customer= new Customer(rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getDate("birthDate").toLocalDate(),
                        rs.getString("phoneNumber"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getString("password"));
            }
            return customer;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    // Update Password
    @Override
    public int updateCustomer(Customer customer) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "UPDATE javadevt_genk_08.Customer SET password=? WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, customer.getPassword());
            ps.setLong(2,customer.getId());
            int result= ps.executeUpdate();
            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }


    }



    @Override
    public int deleteCustomer(Customer customer) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "DELETE FROM javadevt_genk_08.Customer WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setLong(1,customer.getId());
            return ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public int deleteCustomerById(int id) {

        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "DELETE FROM javadevt_genk_08.Customer WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setLong(1,id);
            return ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }
}
