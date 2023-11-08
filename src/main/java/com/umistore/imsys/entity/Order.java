package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "Orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long customerId;

    private Long paymentTransactionId;

    private String orderStatus;

    private BigDecimal totalAmount;

    @Column(nullable = false, updatable = false, insertable = false)
    private Timestamp orderDate;

    @PrePersist
    protected void onCreate() {
        orderDate = new java.sql.Timestamp(System.currentTimeMillis());
    }

}
