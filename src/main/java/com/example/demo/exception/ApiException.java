package com.example.demo.exception;

import lombok.*;
import org.apache.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {

    private String message;
    private ZonedDateTime timestamp;

}
