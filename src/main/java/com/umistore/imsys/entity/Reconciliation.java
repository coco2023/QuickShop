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
@Table(name = "Reconciliations")
public class Reconciliation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reconciliationId;

    private Long inventoryId;
    private Integer recordedQuantity;
    private Integer observedQuantity;

    @Column(name = "difference", insertable = false, updatable = false)
    private Integer difference;

    @Column(nullable = false, updatable = false, insertable = false)
    private Timestamp reconciliationDate;

    private String notes;

    @PrePersist
    protected void onCreate() {
        if (reconciliationDate == null) {
            reconciliationDate = new Timestamp(System.currentTimeMillis());
        }
    }

}
