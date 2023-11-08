package com.umistore.imsys.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Embeddable
public class SKUAttributeId implements Serializable {

    private Integer skuId;

    private Integer attributeValueId;

}
