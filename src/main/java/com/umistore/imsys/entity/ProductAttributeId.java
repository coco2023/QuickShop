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
public class ProductAttributeId implements Serializable {

    private Integer productId;

    private Integer attributeValueId;

}
