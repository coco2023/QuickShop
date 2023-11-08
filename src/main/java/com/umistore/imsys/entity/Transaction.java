package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "Transactions")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long skuId;
    private Integer quantity;
    private String transactionType;
    private Long orderId;
    private Long paymentTransactionId;

    @Column(nullable = false, updatable = false, insertable = false)
    private Timestamp transactionDate;


    @PrePersist
    protected void onCreate() {
        if (transactionDate == null) {
            transactionDate = new Timestamp(System.currentTimeMillis());
        }
    }

}
