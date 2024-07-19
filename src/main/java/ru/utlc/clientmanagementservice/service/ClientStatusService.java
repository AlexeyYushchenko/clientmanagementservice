package ru.utlc.clientmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusReadDto;
import ru.utlc.clientmanagementservice.exception.ClientStatusCreationException;
import ru.utlc.clientmanagementservice.mapper.ClientStatusMapper;
import ru.utlc.clientmanagementservice.repository.ClientStatusRepository;
import java.util.List;
import java.util.Optional;
import static ru.utlc.clientmanagementservice.constants.CacheNames.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientStatusService {
    private final ClientStatusRepository clientStatusRepository;
    private final ClientStatusMapper clientStatusMapper;
    private final CacheManager cacheManager;

    @Cacheable(value = CLIENT_STATUSES, key = "'all'")
    public List<ClientStatusReadDto> findAll() {
        List<ClientStatusReadDto> statuses = clientStatusRepository.findAll().stream()
                .map(clientStatusMapper::toDto)
                .toList();
        // Cache each individual status
        statuses.forEach(status -> cacheManager.getCache(CLIENT_STATUSES).put(status.id(), status));
        return statuses;
    }

    @Cacheable(value = CLIENT_STATUSES, key = "#p0")
    public Optional<ClientStatusReadDto> findById(Integer id) {
        return clientStatusRepository.findById(id).map(clientStatusMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = CLIENT_STATUSES, allEntries = true)
    @CachePut(value = CLIENT_STATUSES, key = "#result.id")
    public ClientStatusReadDto create(ClientStatusCreateUpdateDto createUpdateDto) throws ClientStatusCreationException {
        return Optional.of(createUpdateDto)
                .map(clientStatusMapper::toEntity)
                .map(clientStatusRepository::save)
                .map(clientStatusMapper::toDto)
                .orElseThrow(() -> new ClientStatusCreationException("error.entity.clientStatus.creation"));
    }

    @Transactional
    @CacheEvict(value = CLIENT_STATUSES, allEntries = true)
    @CachePut(value = CLIENT_STATUSES, key = "#result.id")
    public Optional<ClientStatusReadDto> update(Integer id, ClientStatusCreateUpdateDto dto) {
        return clientStatusRepository.findById(id)
                .map(entity -> clientStatusMapper.update(entity, dto))
                .map(clientStatusRepository::saveAndFlush)
                .map(clientStatusMapper::toDto);
    }

    @Transactional
    @CacheEvict(value = CLIENT_STATUSES, allEntries = true)
    public boolean delete(Integer id) {
        return clientStatusRepository.findById(id)
                .map(clientStatus -> {
                    clientStatusRepository.delete(clientStatus);
                    clientStatusRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
