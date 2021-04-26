package service.impl;

import entity.Order;
import repo.OrderRepository;
import repo.impl.OrderRepositoryImpl;
import service.OrderService;

import java.util.List;

/**
 * Created by Moon on 24/04/2021
 */
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    @Override
    public int save(Order order) {
        return orderRepository.save(order);
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
