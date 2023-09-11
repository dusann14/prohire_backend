package com.example.demo.model.mapper.impl;

import com.example.demo.model.domain.ConfirmationToken;
import com.example.demo.model.domainDTO.ConfirmationTokenDTO;
import com.example.demo.model.mapper.EntityDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ConfirmationTokenMapper implements EntityDTOMapper<ConfirmationToken, ConfirmationTokenDTO> {

    private final AppUserMapper appUserMapper;
    @Override
    public ConfirmationToken mapDTOToEntity(ConfirmationTokenDTO confirmationTokenDTO) {
        return ConfirmationToken.builder()
                .id(confirmationTokenDTO.getId())
                .token(confirmationTokenDTO.getToken())
                .appUser(appUserMapper.mapDTOToEntity(confirmationTokenDTO.getAppUserDTO()))
                .build();
    }

    @Override
    public ConfirmationTokenDTO mapEntityToDTO(ConfirmationToken confirmationToken) {
        return ConfirmationTokenDTO.builder()
                .id(confirmationToken.getId())
                .token(confirmationToken.getToken())
                .appUserDTO(appUserMapper.mapEntityToDTO(confirmationToken.getAppUser()))
                .build();
    }

    @Override
    public List<ConfirmationToken> mapListDTOToListEntity(List<ConfirmationTokenDTO> confirmationTokenDTOS) {

        List<ConfirmationToken> confirmationTokenList = new ArrayList<>();

        for (ConfirmationTokenDTO confirmationTokenDTO:
             confirmationTokenDTOS) {
            confirmationTokenList.add(mapDTOToEntity(confirmationTokenDTO));
        }
        return confirmationTokenList;
    }

    @Override
    public List<ConfirmationTokenDTO> mapListEntityToListDTO(List<ConfirmationToken> confirmationTokens) {

        List<ConfirmationTokenDTO> confirmationTokenDTOS = new ArrayList<>();

        for (ConfirmationToken confirmationToken:
             confirmationTokens) {
            confirmationTokenDTOS.add(mapEntityToDTO(confirmationToken));
        }
        return confirmationTokenDTOS;
    }


}
