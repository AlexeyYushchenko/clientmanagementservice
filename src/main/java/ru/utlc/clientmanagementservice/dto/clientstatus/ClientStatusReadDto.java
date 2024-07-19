package ru.utlc.clientmanagementservice.dto.clientstatus;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record ClientStatusReadDto(
        Integer id,
        String name,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {}


