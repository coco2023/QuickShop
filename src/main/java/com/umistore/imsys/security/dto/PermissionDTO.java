package com.umistore.imsys.security.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class PermissionDTO {

    private Integer permissionID;

    private String permissionName;

    private String description;  // e.g., READ_PRODUCTS, CREATE_ORDER

}
