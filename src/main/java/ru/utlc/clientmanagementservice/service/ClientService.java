package ru.utlc.clientmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.utlc.clientmanagementservice.dto.client.ClientCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.client.ClientReadDto;
import ru.utlc.clientmanagementservice.exception.ClientCreationException;
import ru.utlc.clientmanagementservice.mapper.ClientMapper;
import ru.utlc.clientmanagementservice.model.Client;
import ru.utlc.clientmanagementservice.repository.BusinessTypeRepository;
import ru.utlc.clientmanagementservice.repository.ClientRepository;
import ru.utlc.clientmanagementservice.repository.ClientStatusRepository;
import ru.utlc.clientmanagementservice.repository.IndustryTypeRepository;

import java.util.List;
import java.util.Optional;

import static ru.utlc.clientmanagementservice.constants.CacheNames.CLIENTS;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientStatusRepository clientStatusRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private final IndustryTypeRepository industryTypeRepository;
    private final CacheManager cacheManager;

    @Cacheable(value = CLIENTS, key = "'all'")
    public List<ClientReadDto> findAll() {
        List<ClientReadDto> list = clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();

        list.forEach(entity -> cacheManager.getCache(CLIENTS).put(entity.id(), entity));
        return list;
    }

    @Cacheable(value = CLIENTS, key = "#p0")
    public Optional<ClientReadDto> findById(Integer id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = CLIENTS, allEntries = true)
    @CachePut(value = CLIENTS, key = "#result.id")
    public ClientReadDto create(ClientCreateUpdateDto dto) throws ClientCreationException {
        return Optional.of(dto)
                .map(clientMapper::toEntity)
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(clientRepository::save)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ClientCreationException("error.entity.client.creation"));
    }

    @Transactional
    @CacheEvict(value = CLIENTS, allEntries = true)
    @CachePut(value = CLIENTS, key = "#result.id")
    public Optional<ClientReadDto> update(Integer id, ClientCreateUpdateDto dto) {
        return clientRepository.findById(id)
                .map(entity -> clientMapper.update(entity, dto))
                .map(entity -> setUpOtherEntitiesToMainEntity(entity, dto))
                .map(clientRepository::saveAndFlush)
                .map(clientMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = CLIENTS, allEntries = true)
    public boolean delete(Integer id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    clientRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    private Client setUpOtherEntitiesToMainEntity(Client entity, ClientCreateUpdateDto dto) {
        var clientStatus = Optional.ofNullable(dto.clientStatusId())
                .flatMap(clientStatusRepository::findById)
                .orElse(null);
        entity.setClientStatus(clientStatus);
        var industryType = Optional.ofNullable(dto.industryTypeId())
                .flatMap(industryTypeRepository::findById)
                .orElse(null);
        entity.setIndustryType(industryType);
        var businessType = Optional.ofNullable(dto.businessTypeId())
                .flatMap(businessTypeRepository::findById)
                .orElse(null);
        entity.setBusinessType(businessType);

        return entity;
    }
}