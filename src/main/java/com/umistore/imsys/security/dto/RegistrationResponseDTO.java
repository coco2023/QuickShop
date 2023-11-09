package com.umistore.imsys.security.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class RegistrationResponseDTO {

    private Integer userID;

    private String username;

    private String userType;

}
