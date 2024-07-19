package ru.utlc.clientmanagementservice.dto.client;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientCreateUpdateDto(
        @NotNull(message = "validation.client.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.client.name.pattern")
        @Size(min = 2, max = 50, message = "validation.client.name.size")
        String name,

        @Size(max = 100, message = "validation.client.fullName.size")
        String fullName,

        @NotNull(message = "validation.client.clientStatus.required")
        Integer clientStatusId,

        @NotNull(message = "validation.client.businessType.required")
        Integer businessTypeId,

        @NotNull(message = "validation.client.industryType.required")
        Integer industryTypeId,

        @Size(max = 255, message = "validation.client.address.size")
        String address
) {
}
