package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "AttributeValues")
public class AttributeValue {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeValueID;

    @Column(nullable = false)
    private Integer attributeID;

    @Column(nullable = false)
    private String value;

}
