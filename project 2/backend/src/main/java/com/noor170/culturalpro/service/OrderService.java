package com.noor170.culturalpro.service;

import com.noor170.culturalpro.model.OrderEntity;
import com.noor170.culturalpro.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity placeOrder(OrderEntity order) {
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }
}
