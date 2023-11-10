package com.umistore.imsys.security.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class RolePermissionDto {

    private Integer rolePermissionID;

    private Integer roleId;

    private Integer permissionId;

}
