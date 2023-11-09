package com.umistore.imsys.security.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "UserRoles")
public class UserRole {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleID;

    @Column(nullable = false)
    private Integer userID;

    @Column(nullable = false)
    private Integer roleID;

}
