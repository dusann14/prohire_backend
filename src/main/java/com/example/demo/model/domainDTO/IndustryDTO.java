package com.example.demo.model.domainDTO;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndustryDTO {

    private Long id;
    private String name;

}
