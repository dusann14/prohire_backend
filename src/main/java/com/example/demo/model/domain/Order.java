package com.example.demo.model.domain;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;


}
