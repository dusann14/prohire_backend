package com.example.demo.model.domainDTO;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationTokenDTO {

    private Long id;
    private String token;
    private AppUserDTO appUserDTO;

}
