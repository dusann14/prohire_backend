package com.example.demo.model.request_response;


import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Object object;
    private String error;

}
