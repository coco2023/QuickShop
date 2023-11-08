package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AttributeGroupMappingId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "AttributeGroupMapping")
public class AttributeGroupMapping implements Serializable {

//    @Id
//    @ManyToOne
//    @JoinColumn(name = "attributeGroupID")
//    private AttributeGroup attributeGroup;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "attributeID")
//    private Attribute attribute;

    @Id
    @Column(name = "AttributeGroupID")
    private Integer attributeGroupId;

    @Id
    @Column(name = "AttributeID")
    private Integer attributeId;

}
