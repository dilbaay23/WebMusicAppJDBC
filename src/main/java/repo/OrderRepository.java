package repo;

import entity.Order;

import java.util.List;

/**
 * Created by Moon on 24/04/2021
 */
public interface OrderRepository {
    int save(Order order);
    List<Order> retrieveAll();
    Order getOrderById(int id);
    Order getOrderByCustomerId(int customerId);
    int updateOrder(Order order);
    int deleteOrder(Order order);
    int deleteOrderById(int id);
    int deleteOrderByCustomerId(int customerId);
}
