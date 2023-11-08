package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Embeddable
public class AttributeGroupMappingId implements Serializable {

    private Integer attributeGroupId;

    private Integer attributeId;

}
