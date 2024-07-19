package ru.utlc.clientmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.utlc.clientmanagementservice.model.IndustryType;

public interface IndustryTypeRepository extends JpaRepository<IndustryType, Integer> {

}