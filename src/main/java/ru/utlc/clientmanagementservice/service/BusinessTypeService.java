package ru.utlc.clientmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.utlc.clientmanagementservice.dto.businesstype.BusinessTypeCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.businesstype.BusinessTypeReadDto;
import ru.utlc.clientmanagementservice.exception.BusinessTypeCreationException;
import ru.utlc.clientmanagementservice.mapper.BusinessTypeMapper;
import ru.utlc.clientmanagementservice.repository.BusinessTypeRepository;

import java.util.List;
import java.util.Optional;

import static ru.utlc.clientmanagementservice.constants.CacheNames.BUSINESS_TYPES;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessTypeService {
    private final BusinessTypeRepository businessTypeRepository;
    private final BusinessTypeMapper businessTypeMapper;
    private final CacheManager cacheManager;

    @Cacheable(value = BUSINESS_TYPES, key = "'all'")
    public List<BusinessTypeReadDto> findAll() {
        List<BusinessTypeReadDto> list = businessTypeRepository.findAll().stream()
                .map(businessTypeMapper::toDto)
                .toList();

        list.forEach(entity -> cacheManager.getCache(BUSINESS_TYPES).put(entity.id(), entity));
        return list;
    }

    @Cacheable(value = BUSINESS_TYPES, key = "#p0")
    public Optional<BusinessTypeReadDto> findById(Integer id) {
        return businessTypeRepository.findById(id).map(businessTypeMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = BUSINESS_TYPES, allEntries = true)
    @CachePut(value = BUSINESS_TYPES, key = "#result.id")
    public BusinessTypeReadDto create(BusinessTypeCreateUpdateDto createUpdateDto) throws BusinessTypeCreationException {
        return Optional.of(createUpdateDto)
                .map(businessTypeMapper::toEntity)
                .map(businessTypeRepository::save)
                .map(businessTypeMapper::toDto)
                .orElseThrow(() -> new BusinessTypeCreationException("error.entity.businessType.creation"));
    }

    @Transactional
    @CacheEvict(value = BUSINESS_TYPES, allEntries = true)
    @CachePut(value = BUSINESS_TYPES, key = "#result.id")
    public Optional<BusinessTypeReadDto> update(Integer id, BusinessTypeCreateUpdateDto dto) {
        return businessTypeRepository.findById(id)
                .map(entity -> businessTypeMapper.update(entity, dto))
                .map(businessTypeRepository::saveAndFlush)
                .map(businessTypeMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = BUSINESS_TYPES, allEntries = true)
    public boolean delete(Integer id) {
        return businessTypeRepository.findById(id)
                .map(businessType -> {
                    businessTypeRepository.delete(businessType);
                    businessTypeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
