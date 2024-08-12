package ru.utlc.clientmanagementservice.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ru.utlc.clientmanagementservice.dto.businesstype.BusinessTypeReadDto;
import ru.utlc.clientmanagementservice.dto.client.ClientReadDto;
import ru.utlc.clientmanagementservice.dto.clientstatus.ClientStatusReadDto;
import ru.utlc.clientmanagementservice.dto.industrytype.IndustryTypeReadDto;
import ru.utlc.clientmanagementservice.localization.BusinessTypeLocalization;
import ru.utlc.clientmanagementservice.localization.ClientStatusLocalization;
import ru.utlc.clientmanagementservice.localization.IndustryTypeLocalization;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocalizationUtil {


    public <T> T toLocalizedDto(T dto) {
        final String language = LocaleContextHolder.getLocale().getLanguage();
        try {
            if (dto instanceof ClientStatusReadDto) {
                return (T) localizeClientStatus((ClientStatusReadDto) dto, language);
            } else if (dto instanceof BusinessTypeReadDto) {
                return (T) localizeBusinessType((BusinessTypeReadDto) dto, language);
            } else if (dto instanceof IndustryTypeReadDto) {
                return (T) localizeIndustryType((IndustryTypeReadDto) dto, language);
            } else if (dto instanceof ClientReadDto) {
                return (T) localizeClient((ClientReadDto) dto, language);
            }
        } catch (Exception e) {
            log.error("Exception in {} with cause: {}", LocalizationUtil.class.getSimpleName(), e.getMessage());
        }
        return null;
    }

    private ClientStatusReadDto localizeClientStatus(ClientStatusReadDto dto, String language) {
        final Map<String, ClientStatusLocalization> localizations = dto.localizations();
        final ClientStatusLocalization localization = localizations.getOrDefault(language, new ClientStatusLocalization(dto.name()));
        final String name = localization.getLocalizedName() == null ? dto.name() : localization.getLocalizedName();

        return new ClientStatusReadDto(
                dto.id(),
                name,
                Collections.emptyMap(),
                dto.auditingInfoDto()
        );
    }

    private BusinessTypeReadDto localizeBusinessType(BusinessTypeReadDto dto, String language) {

        final Map<String, BusinessTypeLocalization> localizations = dto.localizations();
        final BusinessTypeLocalization localization = localizations.getOrDefault(language, new BusinessTypeLocalization(dto.name(), dto.description()));
        final String name = localization.getLocalizedName() == null ? dto.name() : localization.getLocalizedName();
        final String description = localization.getLocalizedDescription() == null ? dto.description() : localization.getLocalizedDescription();

        return new BusinessTypeReadDto(
                dto.id(),
                name,
                description,
                Collections.emptyMap(),
                dto.auditingInfoDto()
        );
    }
    private IndustryTypeReadDto localizeIndustryType(IndustryTypeReadDto dto, String language) {
        final Map<String, IndustryTypeLocalization> localizations = dto.localizations();
        final IndustryTypeLocalization localization = localizations.getOrDefault(language, new IndustryTypeLocalization(dto.name(), dto.description()));
        final String name = localization.getLocalizedName() == null ? dto.name() : localization.getLocalizedName();
        final String description = localization.getLocalizedDescription() == null ? dto.description() : localization.getLocalizedDescription();

        return new IndustryTypeReadDto(
                dto.id(),
                name,
                description,
                Collections.emptyMap(),
                dto.auditingInfoDto()
        );
    }
    private ClientReadDto localizeClient(ClientReadDto dto, String language) {

        return new ClientReadDto(
                dto.id(),
                dto.name(),
                dto.fullName(),
                localizeClientStatus(dto.clientStatus(), language),
                localizeBusinessType(dto.businessType(),language),
                localizeIndustryType(dto.industryType(),language),
                dto.address(),
                dto.auditingInfoDto()
        );
    }
}