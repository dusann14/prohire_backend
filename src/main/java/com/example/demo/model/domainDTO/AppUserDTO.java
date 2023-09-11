package com.example.demo.model.domainDTO;

import com.example.demo.model.domain.ConfirmationToken;
import com.example.demo.model.domain.UserType;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppUserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType appUserRole;
    private String token;
    private ResumeDTO resumeDTO;

}
