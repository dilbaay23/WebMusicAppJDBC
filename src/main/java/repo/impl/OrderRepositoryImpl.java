package repo.impl;

import entity.Order;
import repo.OrderRepository;

import java.sql.*;
import java.util.List;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 24/04/2021
 */
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public int save(Order order) {

        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

           // String[] generatedId = { "ID" };
            String sql= "INSERT INTO javadevt_genk_08.Order (customerId, date, status) VALUES (?, ?, ?)";

            PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getCustomerId());
            ps.setTimestamp(2, getCurrentTimeStamp());
            ps.setString(3, order.getStatus());
            int result= ps.executeUpdate();
            int generatedId=-1;
            if (result > 0) {

                try {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        generatedId = (int) rs.getLong(1);

                    }

                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                    throw  new RuntimeException(throwables);
                }
            }
            return generatedId;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }
    private static Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new Timestamp(today.getTime());
    }

    @Override
    public List<Order> retrieveAll() {
        return null;
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public Order getOrderByCustomerId(int customerId) {
        return null;
    }

    @Override
    public int updateOrder(Order order) {
        return 0;
    }

    @Override
    public int deleteOrder(Order order) {
        return 0;
    }

    @Override
    public int deleteOrderById(int id) {
        return 0;
    }

    @Override
    public int deleteOrderByCustomerId(int customerId) {
        return 0;
    }
}
