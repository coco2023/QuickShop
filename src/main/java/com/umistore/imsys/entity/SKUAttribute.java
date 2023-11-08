package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(SKUAttributeId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "SKUAttributes")
public class SKUAttribute implements Serializable {

//    @Id
//    @ManyToOne
//    @JoinColumn(name = "skuID")
//    private SKU sku;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "attributeValueID")
//    private AttributeValue attributeValue;

    @Id
    @Column(name = "SKU_ID")
    private Integer skuId;

    @Id
    @Column(name = "AttributeValueID")
    private Integer attributeValueId;

}
