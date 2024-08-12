package ru.utlc.clientmanagementservice.dto.clientstatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.utlc.clientmanagementservice.localization.ClientStatusLocalization;
import ru.utlc.clientmanagementservice.localization.IndustryTypeLocalization;

import java.util.HashMap;
import java.util.Map;

public record ClientStatusCreateUpdateDto(
        @NotNull(message = "validation.clientStatus.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.clientStatus.name.pattern")
        @Size(min = 2, max = 50, message = "validation.clientStatus.name.size")
        String name,
        Map<String, ClientStatusLocalization> localizations
) {
        public ClientStatusCreateUpdateDto {
                if (localizations == null) {
                        localizations = new HashMap<>();
                }
        }
}
