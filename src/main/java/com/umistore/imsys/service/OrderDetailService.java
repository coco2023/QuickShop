package com.umistore.imsys.service;

import com.umistore.imsys.entity.OrderDetail;
import com.umistore.imsys.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(Integer id) {
        orderDetailRepository.deleteById(id);
    }

    public OrderDetail updateOrderDetail(Integer orderDetailId, OrderDetail newOrderDetailData) {
        return orderDetailRepository.findById(orderDetailId)
                .map(orderDetail -> {
                    orderDetail.setQuantityOrdered(newOrderDetailData.getQuantityOrdered());
                    orderDetail.setPriceAtOrder(newOrderDetailData.getPriceAtOrder());
                    // Add more fields to update as necessary
                    return orderDetailRepository.save(orderDetail);
                }).orElseThrow(() -> new EntityNotFoundException("OrderDetail not found with id " + orderDetailId));
    }

    public List<OrderDetail> searchByOrder(Integer orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

}
