package com.example.demo.service;

import com.example.demo.exception.custom.AccountAlreadyActivatedException;
import com.example.demo.exception.custom.TokenExpiredException;
import com.example.demo.model.domain.ConfirmationToken;
import com.example.demo.model.domainDTO.ConfirmationTokenDTO;
import com.example.demo.model.mapper.impl.ConfirmationTokenMapper;
import com.example.demo.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final AppUserService appUserService;

    private final ConfirmationTokenMapper confirmationTokenMapper;

    public ConfirmationToken saveConfirmationToken(ConfirmationToken token){
        return confirmationTokenRepository.save(token);
    }

    @Transactional
    public ConfirmationTokenDTO confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(() ->
                        new DataIntegrityViolationException("Token ne postoji"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new AccountAlreadyActivatedException("Nalog je već aktiviran");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            confirmationTokenRepository.delete(confirmationToken);
            appUserService.deleteAppUser(confirmationToken.getAppUser());
            throw new TokenExpiredException("Token je istekao, registruj se ponovo");
        }

        setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());

        return confirmationTokenMapper.mapEntityToDTO(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
