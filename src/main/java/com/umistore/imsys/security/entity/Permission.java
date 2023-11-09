package com.umistore.imsys.security.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "Permissions")
public class Permission {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionID;

    @Column(unique = true, nullable = false)
    private String permissionName;

    @Column(columnDefinition = "TEXT")
    private String description;

}
