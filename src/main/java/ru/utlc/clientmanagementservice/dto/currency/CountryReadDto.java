package ru.utlc.clientmanagementservice.dto.currency;


import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;

import java.util.Map;

public record CountryReadDto(
        Integer id,
        String code,
        String name,
        Boolean enabled,
        Map<String, String> nameLocales,
        AuditingInfoDto auditingInfoDto
) {}
