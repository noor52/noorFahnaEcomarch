package com.noor170.culturalpro.controller;

import com.noor170.culturalpro.builder.OrderBuilder;
import com.noor170.culturalpro.model.OrderEntity;
import com.noor170.culturalpro.model.OrderItem;
import com.noor170.culturalpro.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        List<OrderItem> orderItems = orderRequest.getItems().stream()
                .map(dto -> new OrderItem(
                        dto.getProductId(),
                        dto.getName(),
                        dto.getSize(),
                        dto.getQuantity(),
                        dto.getPrice()
                ))
                .collect(Collectors.toList());

        OrderEntity order = OrderBuilder.create()
                .addCustomer(orderRequest.getCustomerName())
                .addAddress(orderRequest.getAddress())
                .addPaymentMethod(orderRequest.getPaymentMethod())
                .addItems(orderItems)
                .build();

        OrderEntity savedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    public static class OrderRequestDTO {
        private String customerName;
        private String address;
        private String paymentMethod;
        private List<OrderItemDTO> items;

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public List<OrderItemDTO> getItems() {
            return items;
        }

        public void setItems(List<OrderItemDTO> items) {
            this.items = items;
        }
    }

    public static class OrderItemDTO {
        private Long productId;
        private String name;
        private String size;
        private Integer quantity;
        private Double price;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
