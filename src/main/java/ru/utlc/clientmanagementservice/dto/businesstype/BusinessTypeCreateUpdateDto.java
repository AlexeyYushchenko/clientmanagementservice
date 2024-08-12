package ru.utlc.clientmanagementservice.dto.businesstype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.utlc.clientmanagementservice.localization.BusinessTypeLocalization;

import java.util.HashMap;
import java.util.Map;

public record BusinessTypeCreateUpdateDto(
        @NotNull(message = "validation.businessType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.businessType.name.pattern")
        @Size(min = 2, max = 100, message = "validation.businessType.name.size")
        String name,
        String description,
        Map<String, BusinessTypeLocalization> localizations
) {
    public BusinessTypeCreateUpdateDto {
        if (localizations == null) {
            localizations = new HashMap<>();
        }
    }
}