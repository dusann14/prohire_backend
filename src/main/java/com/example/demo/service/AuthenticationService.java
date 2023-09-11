package com.example.demo.service;

import com.example.demo.email.EmailService;
import com.example.demo.model.domain.*;
import com.example.demo.model.domainDTO.AppUserDTO;
import com.example.demo.model.domainDTO.ConfirmationTokenDTO;
import com.example.demo.model.domainDTO.ResumeDTO;
import com.example.demo.model.mapper.impl.AppUserMapper;
import com.example.demo.model.request_response.*;
import com.example.demo.model.domain.ConfirmationToken;
import com.example.demo.security.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final ResumeService resumeService;
    private final EmployerService employerService;
    private final JobSeekerService jobSeekerService;

    private final AppUserMapper appUserMapper;

    public AppUserDTO authenticate(AppUser appUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUser.getEmail(),
                        appUser.getPassword()
                )
        );

        appUser = appUserService.findByEmail(appUser.getEmail());

        AppUserDTO appUserDTO = appUserMapper.mapEntityToDTO(appUser);

        String token = jwtService.generateToken(appUser);

        appUserDTO.setToken(token);

        if(appUserDTO.getAppUserRole() == UserType.JOB_SEEKER){
            ResumeDTO resumeDTO = resumeService.findResumeByJobSeeker(appUserDTO.getId());
            appUserDTO.setResumeDTO(resumeDTO);
        }

        return appUserDTO;
    }

    public ConfirmationTokenDTO confirmToken(String token) {
        return confirmationTokenService.confirmToken(token);
    }

    public AppUserDTO register(AppUserDTO appUserDTO) throws MessagingException {

        AppUser appUser = appUserMapper.mapDTOToEntity(appUserDTO);

        String actualToken = "";

        appUser = appUserService.saveUser(appUser);

        if(appUser.getAppUserRole() == UserType.JOB_SEEKER){
            JobSeeker jobSeeker = JobSeeker.builder()
                    .id(appUser.getId())
                    .appUser(appUser)
                    .build();
            jobSeeker = jobSeekerService.saveJobSeeker(jobSeeker);
        }else if(appUser.getAppUserRole() == UserType.EMPLOYER){
            Employer employer = Employer.builder()
                    .id(appUser.getId())
                    .appUser(appUser)
                    .build();
            employer = employerService.saveEmployer(employer);
        }

        actualToken = jwtService.generateToken(appUser);

        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(actualToken)
                .createdAt( LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .appUser(appUser)
                .build();

        confirmationToken = confirmationTokenService.saveConfirmationToken(confirmationToken);

        String link = "http://localhost:3000/congratulation/" + actualToken;

        emailService.send(appUser.getEmail(), link);

        appUserDTO = appUserMapper.mapEntityToDTO(appUser);

        appUserDTO.setToken(confirmationToken.getToken());

        return appUserDTO;
    }

    public AppUserDTO updateUser(RegistrationRequest request) {

        AppUser appUser = appUserService.updateUser(request.getId(), passwordEncoder.encode(request.getPassword()));

        if(appUser.getAppUserRole() == UserType.EMPLOYER){
            Employer employer = employerService.findEmployerById(request.getId());
            employer.setCity(request.getCity());
            employer.setCompanyName(request.getCompanyName());
            employer.setCompanyPhone(request.getPhoneNumber());
            employer.setIndustry(request.getIndustry());
            employer.setNumber(request.getNumber());
            employer.setPIB(request.getPIB());
            employer.setPostalCode(request.getPostalCode());
            employer = employerService.saveEmployer(employer);
        }

        return appUserMapper.mapEntityToDTO(appUser);
    }
}
