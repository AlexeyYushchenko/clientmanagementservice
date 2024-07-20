package ru.utlc.clientmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.utlc.clientmanagementservice.dto.currency.CountryReadDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {
    private final String referenceDataServiceUrl = "/api/v1/countries";
    private final WebClient client;

    public Mono<CountryReadDto> findCountryById(Integer id) {
        return this.client.get()
                .uri(referenceDataServiceUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(CountryReadDto.class);
    }
}
