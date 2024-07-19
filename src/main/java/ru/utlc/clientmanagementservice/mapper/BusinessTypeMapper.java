package ru.utlc.clientmanagementservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.utlc.clientmanagementservice.dto.businesstype.BusinessTypeCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.businesstype.BusinessTypeReadDto;
import ru.utlc.clientmanagementservice.model.BusinessType;

@Mapper
public interface BusinessTypeMapper {
    @Mapping(target = "auditingInfoDto", source = ".")
    BusinessTypeReadDto toDto(BusinessType businessType);  // Entity to DTO

    BusinessType toEntity(BusinessTypeCreateUpdateDto createUpdateDto);  // DTO to Entity

    BusinessType update(@MappingTarget BusinessType businessType, BusinessTypeCreateUpdateDto createUpdateDto);
}
