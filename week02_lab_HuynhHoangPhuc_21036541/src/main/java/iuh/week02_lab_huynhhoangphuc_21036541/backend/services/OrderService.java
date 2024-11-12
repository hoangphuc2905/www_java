package iuh.week02_lab_huynhhoangphuc_21036541.backend.services;


import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Order;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.repositories.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService() {
        orderRepository = new OrderRepository();
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }
}
