package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "Attributes")
public class Attribute {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeID;

    @Column(nullable = false)
    private String name;

}
