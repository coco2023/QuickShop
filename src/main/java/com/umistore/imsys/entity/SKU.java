package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "SKU")
public class SKU {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skuID;

    @Column(name = "productID")
    private Integer productID;

    @Column(nullable = false, unique = true)
    private String sku;

}
