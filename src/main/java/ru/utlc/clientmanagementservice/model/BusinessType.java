package ru.utlc.clientmanagementservice.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "business_type_localization", joinColumns = @JoinColumn(name = "business_type_id"))
    @MapKeyColumn(name = "language_code")
    @Column(name = "localized_name")
    private Map<String, String> nameLocales = new HashMap<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "business_type_localization", joinColumns = @JoinColumn(name = "business_type_id"))
    @MapKeyColumn(name = "language_code")
    @Column(name = "localized_description")
    private Map<String, String> descriptionLocales = new HashMap<>();
}

