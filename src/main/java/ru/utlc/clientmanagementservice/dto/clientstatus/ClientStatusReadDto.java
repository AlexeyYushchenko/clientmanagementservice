package ru.utlc.clientmanagementservice.dto.clientstatus;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;
import ru.utlc.clientmanagementservice.localization.ClientStatusLocalization;

import java.util.Map;

public record ClientStatusReadDto(
        Integer id,
        String name,
        Map<String, ClientStatusLocalization> localizations,
        AuditingInfoDto auditingInfoDto
) {}


