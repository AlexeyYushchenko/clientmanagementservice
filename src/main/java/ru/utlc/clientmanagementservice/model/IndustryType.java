package ru.utlc.clientmanagementservice.model;

import jakarta.persistence.*;
import lombok.*;
import ru.utlc.clientmanagementservice.localization.IndustryTypeLocalization;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper=true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "industry_type")
public class IndustryType extends AuditingEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "industry_type_localization", joinColumns = @JoinColumn(name = "industry_type_id"))
    @MapKeyColumn(name = "language_code")
    private Map<String, IndustryTypeLocalization> localizations = new HashMap<>();
}
