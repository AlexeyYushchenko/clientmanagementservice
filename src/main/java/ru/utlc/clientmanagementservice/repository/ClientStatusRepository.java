package ru.utlc.clientmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.utlc.clientmanagementservice.model.ClientStatus;

public interface ClientStatusRepository extends JpaRepository<ClientStatus, Integer> {

}
