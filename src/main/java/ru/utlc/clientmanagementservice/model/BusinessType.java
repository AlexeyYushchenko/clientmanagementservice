package ru.utlc.clientmanagementservice.model;

import jakarta.persistence.*;
import lombok.*;
import ru.utlc.clientmanagementservice.localization.BusinessTypeLocalization;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "business_type")
public class BusinessType extends AuditingEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "business_type_localization", joinColumns = @JoinColumn(name = "business_type_id"))
    @MapKeyColumn(name = "language_code")
    private Map<String, BusinessTypeLocalization> localizations = new HashMap<>();
}