package ru.utlc.clientmanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusReadDto;
import ru.utlc.clientmanagementservice.model.ClientStatus;

@Mapper
public interface ClientStatusMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    ClientStatusReadDto toDto(ClientStatus client);  // Entity to DTO
    ClientStatus toEntity(ClientStatusCreateUpdateDto createUpdateDto);  // DTO to Entity
    ClientStatus update(@MappingTarget ClientStatus entity, ClientStatusCreateUpdateDto createUpdateDto);
}