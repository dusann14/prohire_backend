package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.AppUser;
import com.example.demo.model.domainDTO.AppUserDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserMapper implements EntityDTOMapper<AppUser, AppUserDTO> {

    @Override
    public AppUser mapDTOToEntity(AppUserDTO appUserDTO) {
        return AppUser.builder()
                .id(appUserDTO.getId())
                .firstName(appUserDTO.getFirstName())
                .lastName(appUserDTO.getLastName())
                .email(appUserDTO.getEmail())
                .appUserRole(appUserDTO.getAppUserRole())
                .build();
    }

    @Override
    public AppUserDTO mapEntityToDTO(AppUser appUser) {
        return AppUserDTO.builder()
                .id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .appUserRole(appUser.getAppUserRole())
                .build();
    }

    @Override
    public List<AppUser> mapListDTOToListEntity(List<AppUserDTO> appUserDTOS) {

        List<AppUser> appUserList = new ArrayList<>();

        for (AppUserDTO appUserDTO:
             appUserDTOS) {
            appUserList.add(mapDTOToEntity(appUserDTO));
        }
        return appUserList;
    }

    @Override
    public List<AppUserDTO> mapListEntityToListDTO(List<AppUser> appUsers) {
        List<AppUserDTO> appUserDTOS = new ArrayList<>();

        for (AppUser appUser:
                appUsers) {
            appUserDTOS.add(mapEntityToDTO(appUser));
        }
        return appUserDTOS;
    }


}
