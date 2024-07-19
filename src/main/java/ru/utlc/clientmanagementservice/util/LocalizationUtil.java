package ru.utlc.clientmanagementservice.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusReadDto;

import java.util.Locale;

@Component
@Slf4j
@RequiredArgsConstructor
public class LocalizationUtil {

    public ClientStatusReadDto toLocalizedDto(ClientStatusReadDto dto, Locale locale) {
        try {
            return new ClientStatusReadDto(
                    dto.id(),
                    dto.nameLocales().getOrDefault(locale.getLanguage(), dto.name()),
                    dto.nameLocales(),
                    dto.auditingInfoDto()
            );
        }catch (Exception e){
            log.error("Exception in {} with cause: {}", LocalizationUtil.class.getSimpleName(), e.getMessage());
            return null;
        }
    }

//    public CurrencyReadDto toLocalizedDto(CurrencyReadDto dto, Locale locale) {
//        return new CurrencyReadDto(
//                dto.id(),
//                dto.code(),
//                dto.nameLocales().getOrDefault(locale.getLanguage(), dto.name()),
//                dto.enabled(),
//                dto.nameLocales(),
//                dto.auditingInfoDto()
//        );
//    }
}