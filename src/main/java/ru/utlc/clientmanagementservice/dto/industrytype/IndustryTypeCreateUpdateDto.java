package ru.utlc.clientmanagementservice.dto.industrytype;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashMap;
import java.util.Map;

public record IndustryTypeCreateUpdateDto(
        @NotNull(message = "validation.industryType.name.required")
        @Pattern(regexp = ".*\\S.*", message = "validation.industryType.name.pattern")
        @Size(min = 2, max = 50, message = "validation.industryType.name.size")
        String name,
        String description,
        Map<String, String> nameLocales,
        Map<String, String> descriptionLocales
) {
        public IndustryTypeCreateUpdateDto {
                if (nameLocales == null) {
                        nameLocales = new HashMap<>();
                }
                if (descriptionLocales == null) {
                        descriptionLocales = new HashMap<>();
                }
        }
}
