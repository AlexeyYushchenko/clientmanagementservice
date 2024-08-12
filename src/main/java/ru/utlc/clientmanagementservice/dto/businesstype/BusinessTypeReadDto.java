package ru.utlc.clientmanagementservice.dto.businesstype;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;
import ru.utlc.clientmanagementservice.localization.BusinessTypeLocalization;

import java.util.Map;

public record BusinessTypeReadDto(
        Integer id,
        String name,
        String description,
        Map<String, BusinessTypeLocalization> localizations,
        AuditingInfoDto auditingInfoDto
) {
}