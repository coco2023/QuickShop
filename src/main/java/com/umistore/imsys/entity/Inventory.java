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
@Table(name = "Inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long productId;
    private Integer quantityAvailable;

    @Column(name = "last_updated", nullable = false, updatable = true, insertable = true)
    private Timestamp lastUpdated;

    @PrePersist
    protected void onCreate() {
        lastUpdated = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Timestamp(System.currentTimeMillis());
    }

}
