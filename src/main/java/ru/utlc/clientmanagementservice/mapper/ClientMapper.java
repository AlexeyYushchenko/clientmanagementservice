package ru.utlc.clientmanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.utlc.clientmanagementservice.dto.client.ClientCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.client.ClientReadDto;
import ru.utlc.clientmanagementservice.model.Client;

@Mapper
public interface ClientMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    ClientReadDto toDto(Client client);

    Client toEntity(ClientCreateUpdateDto createUpdateDto);

    Client update(@MappingTarget Client entity, ClientCreateUpdateDto createUpdateDto);
}
