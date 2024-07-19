package ru.utlc.clientmanagementservice.dto.auditinginfo;

import java.time.LocalDateTime;

public record AuditingInfoDto(
        String createdBy,
        LocalDateTime createdAt,
        String modifiedBy,
        LocalDateTime modifiedAt
) {}
