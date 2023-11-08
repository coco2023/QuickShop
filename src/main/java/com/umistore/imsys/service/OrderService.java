package com.umistore.imsys.service;

import com.umistore.imsys.entity.Order;
import com.umistore.imsys.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Integer orderId, Order newOrderData) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setOrderStatus(newOrderData.getOrderStatus());
                    order.setTotalAmount(newOrderData.getTotalAmount());
                    // Add more fields to update as necessary
                    return orderRepository.save(order);
                }).orElseThrow(() -> new EntityNotFoundException("Order not found with id " + orderId));
    }

    public List<Order> searchByCustomer(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
