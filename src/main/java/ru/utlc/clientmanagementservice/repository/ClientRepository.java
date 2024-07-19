package ru.utlc.clientmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.utlc.clientmanagementservice.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}