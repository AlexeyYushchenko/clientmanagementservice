package ru.utlc.clientmanagementservice.dto.industrytype;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record IndustryTypeReadDto(
        Integer id,
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales,
        AuditingInfoDto auditingInfoDto
) {
}