package com.example.demo.model.request_response;

import com.example.demo.model.domain.City;
import com.example.demo.model.domain.Industry;
import com.example.demo.model.domain.UserType;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Builder
@NoArgsConstructor
public class RegistrationRequest {

    private Long id;
    private String password;
    private String companyName;
    private String PIB;
    private String number;
    private Industry industry;
    private String phoneNumber;
    private String postalCode;
    private City city;


}
