package ru.utlc.clientmanagementservice.dto.businesstype;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record BusinessTypeReadDto(
        Integer id,
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales,
        AuditingInfoDto auditingInfoDto
) {
}
