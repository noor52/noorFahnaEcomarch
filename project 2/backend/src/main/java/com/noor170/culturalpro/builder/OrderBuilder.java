package com.noor170.culturalpro.builder;

import com.noor170.culturalpro.model.OrderEntity;
import com.noor170.culturalpro.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private OrderEntity order;

    private OrderBuilder() {
        this.order = new OrderEntity();
        this.order.setItems(new ArrayList<>());
    }

    public static OrderBuilder create() {
        return new OrderBuilder();
    }

    public OrderBuilder addCustomer(String customerName) {
        this.order.setCustomerName(customerName);
        return this;
    }

    public OrderBuilder addAddress(String address) {
        this.order.setAddress(address);
        return this;
    }

    public OrderBuilder addPaymentMethod(String paymentMethod) {
        this.order.setPaymentMethod(paymentMethod);
        return this;
    }

    public OrderBuilder addItem(OrderItem item) {
        this.order.getItems().add(item);
        return this;
    }

    public OrderBuilder addItems(List<OrderItem> items) {
        this.order.getItems().addAll(items);
        return this;
    }

    public OrderEntity build() {
        double total = this.order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        this.order.setTotal(total);
        return this.order;
    }
}
