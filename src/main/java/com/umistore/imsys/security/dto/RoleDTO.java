package com.umistore.imsys.security.dto;

import lombok.*;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class RoleDTO {

    private Integer roleID;

    private String roleName;

    private String description;

}
