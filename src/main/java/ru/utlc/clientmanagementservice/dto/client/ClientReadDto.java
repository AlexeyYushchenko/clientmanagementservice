package ru.utlc.clientmanagementservice.dto.client;

import ru.utlc.clientmanagementservice.dto.auditinginfo.AuditingInfoDto;
import ru.utlc.clientmanagementservice.dto.businesstype.BusinessTypeReadDto;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusReadDto;
import ru.utlc.clientmanagementservice.dto.industrytype.IndustryTypeReadDto;

public record ClientReadDto(
        Integer id,
        String name,
        String fullName,
        ClientStatusReadDto clientStatus,
        BusinessTypeReadDto businessType,
        IndustryTypeReadDto industryType,
        String address,
        AuditingInfoDto auditingInfoDto
) {
}
