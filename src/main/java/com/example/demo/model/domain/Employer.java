package com.example.demo.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employer{

    @Id
    private Long id;
    private String companyName;
    private String PIB;
    private String number;
    private String companyPhone;
    private String postalCode;
    @ManyToOne
    @JoinColumn(
            name = "city_id"
    )
    private City city;
    @ManyToOne
    @JoinColumn(
            name = "industry_id"
    )
    private Industry industry;

    @OneToOne
    @JoinColumn(
            name = "app_user_id"
    )
    private AppUser appUser;

}
