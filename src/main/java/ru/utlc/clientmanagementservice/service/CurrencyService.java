package ru.utlc.clientmanagementservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.utlc.clientmanagementservice.dto.currency.CurrencyReadDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final String referenceDataServiceUrl = "http://localhost:8000/api/v1/countries";
    private final WebClient client;

    public Mono<CurrencyReadDto> getCurrency(Integer id) {
        return this.client.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CurrencyReadDto.class);
    }
}