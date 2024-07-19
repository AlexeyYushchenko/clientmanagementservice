package ru.utlc.clientmanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.utlc.clientmanagementservice.dto.industrytype.IndustryTypeCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.industrytype.IndustryTypeReadDto;
import ru.utlc.clientmanagementservice.model.IndustryType;

@Mapper
public interface IndustryTypeMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    IndustryTypeReadDto toDto(IndustryType industryType);  // Entity to DTO

    IndustryType toEntity(IndustryTypeCreateUpdateDto industryTypeCreateUpdateDto);  // DTO to Entity

    IndustryType update(@MappingTarget IndustryType industryType, IndustryTypeCreateUpdateDto industryTypeCreateUpdateDto);
}
