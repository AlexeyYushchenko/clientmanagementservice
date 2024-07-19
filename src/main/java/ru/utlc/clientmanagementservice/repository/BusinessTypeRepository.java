package ru.utlc.clientmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.utlc.clientmanagementservice.model.BusinessType;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {
}
