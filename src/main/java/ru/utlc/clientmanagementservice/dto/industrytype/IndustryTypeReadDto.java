package ru.utlc.clientmanagementservice.dto.industrytype;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;
import ru.utlc.clientmanagementservice.localization.IndustryTypeLocalization;

import java.util.Map;

public record IndustryTypeReadDto(
        Integer id,
        String name,
        String description,
        Map<String, IndustryTypeLocalization> localizations,
        AuditingInfoDto auditingInfoDto
) {
}