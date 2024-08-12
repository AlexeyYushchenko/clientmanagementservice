package ru.utlc.clientmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.utlc.clientmanagementservice.model.BusinessType;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer> {
}
