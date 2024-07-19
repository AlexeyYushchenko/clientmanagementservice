package ru.utlc.clientmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.utlc.clientmanagementservice.dto.industrytype.IndustryTypeCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.industrytype.IndustryTypeReadDto;
import ru.utlc.clientmanagementservice.exception.IndustryTypeCreationException;
import ru.utlc.clientmanagementservice.mapper.IndustryTypeMapper;
import ru.utlc.clientmanagementservice.repository.IndustryTypeRepository;

import java.util.List;
import java.util.Optional;

import static ru.utlc.clientmanagementservice.constants.CacheNames.INDUSTRY_TYPES;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IndustryTypeService {
    private final IndustryTypeRepository industryTypeRepository;
    private final IndustryTypeMapper industryTypeMapper;

    @Cacheable(INDUSTRY_TYPES)
    public List<IndustryTypeReadDto> findAll() {
        return industryTypeRepository.findAll().stream()
                .map(industryTypeMapper::toDto)
                .toList();
    }

    @Cacheable(value = INDUSTRY_TYPES, key="#p0")
    public Optional<IndustryTypeReadDto> findById(Integer id) {
        return industryTypeRepository.findById(id).map(industryTypeMapper::toDto);
    }

    @Transactional
    @CachePut(value = INDUSTRY_TYPES, key = "#result.id")
    public IndustryTypeReadDto create(IndustryTypeCreateUpdateDto createUpdateDto) throws IndustryTypeCreationException {
        return Optional.of(createUpdateDto)
                .map(industryTypeMapper::toEntity)
                .map(industryTypeRepository::save)
                .map(industryTypeMapper::toDto)
                .orElseThrow(() -> new IndustryTypeCreationException("error.entity.industryType.creation"));
    }

    @Transactional
    @CachePut(value = INDUSTRY_TYPES, key="#p0")
    public Optional<IndustryTypeReadDto> update(Integer id, IndustryTypeCreateUpdateDto dto) {
        return industryTypeRepository.findById(id)
                .map(entity -> industryTypeMapper.update(entity, dto))
                .map(industryTypeRepository::saveAndFlush)
                .map(industryTypeMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = INDUSTRY_TYPES, key="#p0")
    public boolean delete(Integer id) {
        return industryTypeRepository.findById(id)
                .map(industryType -> {
                    industryTypeRepository.delete(industryType);
                    industryTypeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}