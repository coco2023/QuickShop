package com.umistore.imsys.security.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "RolePermissions")
public class RolePermission {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolePermissionID;

    @Column(nullable = false)
    private Integer roleID;

    @Column(nullable = false)
    private Integer permissionID;

}
