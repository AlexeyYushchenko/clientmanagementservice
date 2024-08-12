package ru.utlc.clientmanagementservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import ru.utlc.clientmanagementservice.dto.country.CountryReadDto;
import ru.utlc.clientmanagementservice.service.CountryService;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testFindCountryById() {
        Mono<CountryReadDto> countryMono = countryService.findCountryById(1);
        countryMono.subscribe(country -> System.out.println(country));
    }
}
