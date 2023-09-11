package com.example.demo.model.domainDTO;


import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {

    private String id;
    private String name;
    private String url;
    private byte[] cvFile;

}
