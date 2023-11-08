package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "AttributeGroups")
public class AttributeGroup {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeGroupID;

    @Column(nullable = false)
    private String groupName;

}
