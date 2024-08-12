package ru.utlc.clientmanagementservice.dto.industrytype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.utlc.clientmanagementservice.localization.IndustryTypeLocalization;

import java.util.HashMap;
import java.util.Map;

public record IndustryTypeCreateUpdateDto(
        @NotNull(message = "validation.industryType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.industryType.name.pattern")
        @Size(min = 2, max = 50, message = "validation.industryType.name.size")
        String name,
        String description,
        Map<String, IndustryTypeLocalization> localizations
) {
    public IndustryTypeCreateUpdateDto {
        if (localizations == null) {
            localizations = new HashMap<>();
        }
    }
}
