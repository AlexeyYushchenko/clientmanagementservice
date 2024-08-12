package ru.utlc.clientmanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.utlc.clientmanagementservice.localization.ClientStatusLocalization;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_status")
public class ClientStatus extends AuditingEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, length = 50)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "client_status_localization", joinColumns = @JoinColumn(name = "client_status_id"))
    @MapKeyColumn(name = "language_code")
    private Map<String, ClientStatusLocalization> localizations = new HashMap<>();

}