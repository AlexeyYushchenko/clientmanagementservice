package ru.utlc.clientmanagementservice.localization;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BusinessTypeLocalization {
    private String localizedName;
    private String localizedDescription;
}
