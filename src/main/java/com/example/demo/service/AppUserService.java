package com.example.demo.service;

import com.example.demo.model.domain.AppUser;
import com.example.demo.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService{

    private final AppUserRepository appUserRepository;

    public AppUser saveUser(AppUser appUser){

        Optional<AppUser> user = appUserRepository.findByEmail(appUser.getEmail());

        if(!user.isEmpty()){
            throw new DataIntegrityViolationException("Zauzeta email adresa");
        }

        return appUserRepository.save(appUser);
    }

    public AppUser findByEmail(String email) {

        Optional<AppUser> user = appUserRepository.findByEmail(email);

        if(!user.isPresent()){
            throw new DataIntegrityViolationException("Korisnik ne postoji");
        }

        return user.get();
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public void deleteAppUser(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

    public AppUser updateUser(Long id, String password) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new DataIntegrityViolationException("Korisnik ne postoji"));
        appUser.setPassword(password);
        return appUserRepository.save(appUser);
    }
}
