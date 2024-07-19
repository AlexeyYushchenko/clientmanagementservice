package ru.utlc.clientmanagementservice.dto.clientstatus;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;

public record ClientStatusLocalizedDto(
        Integer id,
        String name,
        AuditingInfoDto auditingInfoDto
) {}


