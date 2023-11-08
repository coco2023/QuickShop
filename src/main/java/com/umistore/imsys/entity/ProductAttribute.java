package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(ProductAttributeId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "ProductAttributes")
public class ProductAttribute implements Serializable {

//    @Id
//    @Column(name = "productID")
//    private Integer productID;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "attributeValueID")
//    private AttributeValue attributeValue;

    @Id
    @Column(name = "productID")
    private Integer productId;

    @Id
    @Column(name = "attributeValueID")
    private Integer attributeValueId;
}
