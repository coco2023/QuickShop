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
@Table(name = "PaymentTransaction")
public class PaymentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentTransactionId;

    private Long orderId;
    private String stripeChargeId;
    private BigDecimal amount;
    private String currency;
    private String paymentStatus;

    @Column(nullable = false, updatable = false, insertable = false)
    private Timestamp paymentDate;

    @PrePersist
    protected void onCreate() {
        paymentDate = new java.sql.Timestamp(System.currentTimeMillis());
    }

}
