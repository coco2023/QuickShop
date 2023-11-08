package com.umistore.imsys.controller;

import com.umistore.imsys.entity.OrderDetail;
import com.umistore.imsys.service.OrderDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
@Api(value = "OrderDetailController", description = "Operations for order Details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/")
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail savedOrderDetail = orderDetailService.createOrderDetail(orderDetail);
        return ResponseEntity.ok(savedOrderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable Integer id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Integer id, @RequestBody OrderDetail orderDetail) {
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(id, orderDetail));
    }

    // Search for order details by order ID
    @GetMapping("/search")
    public ResponseEntity<List<OrderDetail>> searchOrderDetailsByOrder(@RequestParam Integer orderId) {
        List<OrderDetail> orderDetails = orderDetailService.searchByOrder(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
