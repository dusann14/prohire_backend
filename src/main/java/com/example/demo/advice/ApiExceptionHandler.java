package com.example.demo.advice;

import com.example.demo.exception.ApiException;
import com.example.demo.exception.custom.AccountAlreadyActivatedException;
import com.example.demo.exception.custom.JobExpiredException;
import com.example.demo.exception.custom.PastDateException;
import com.example.demo.exception.custom.TokenExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.mail.MessagingException;
import org.apache.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_BAD_REQUEST));
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException e){
        ApiException apiException = ApiException.builder()
                .message("Vaša sesija je istekla, prijavite se ponovo")
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_UNAUTHORIZED));
    }

    @ExceptionHandler(value = {MessagingException.class})
    public ResponseEntity<Object> handleMessagingException(MessagingException e){
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = {AccountAlreadyActivatedException.class})
    public ResponseEntity<Object> handleAccountAlreadyActivatedException(AccountAlreadyActivatedException e){
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_BAD_REQUEST));
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException e){
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_BAD_REQUEST));
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e){
        ApiException apiException = ApiException.builder()
                .message("Ne postoji korisnik sa ovim kredencijalima")
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_UNAUTHORIZED));
    }

    @ExceptionHandler(value = {PastDateException.class})
    public ResponseEntity<Object> handlePastDateException(PastDateException e){
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_BAD_REQUEST));
    }

    @ExceptionHandler(value = {JobExpiredException.class})
    public ResponseEntity<Object> handleJobExpiredException(JobExpiredException e) {
        ApiException apiException = ApiException.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build();
        return new ResponseEntity<>(apiException, HttpStatusCode.valueOf(HttpStatus.SC_BAD_REQUEST));
    }
}
