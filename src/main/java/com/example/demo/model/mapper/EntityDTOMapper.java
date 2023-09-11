package com.example.demo.model.mapper;

import java.util.List;

public interface EntityDTOMapper <Entity, DTO>{

    public Entity mapDTOToEntity(DTO dto);
    public DTO mapEntityToDTO(Entity entity);

    public List<Entity> mapListDTOToListEntity(List<DTO> dtoList);

    public List<DTO> mapListEntityToListDTO(List<Entity> entityList);
}
