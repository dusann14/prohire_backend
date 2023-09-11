package com.example.demo.model.request_response;

import com.example.demo.model.domainDTO.AppUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private AppUserDTO user;
    private String error;

}
