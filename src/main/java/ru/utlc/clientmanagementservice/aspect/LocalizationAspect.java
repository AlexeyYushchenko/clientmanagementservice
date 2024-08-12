package ru.utlc.clientmanagementservice.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.utlc.clientmanagementservice.response.Response;
import ru.utlc.clientmanagementservice.util.LocalizationUtil;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class LocalizationAspect {

    private final LocalizationUtil localizationUtil;

    @Around("execution(* ru.utlc.clientmanagementservice.controller.*.findAll(..))")
    public Object localizeFindAll(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseEntity<List<Object>> responseEntity = (ResponseEntity<List<Object>>) joinPoint.proceed();
        List<Object> result = responseEntity.getBody();
        List<Object> localizedResult = result.stream()
                .map(localizationUtil::toLocalizedDto)
                .toList();
        return ResponseEntity.ok(localizedResult);
    }

    @Around("execution(* ru.utlc.clientmanagementservice.controller.*.findById(..))")
    public Object localizeFindById(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) joinPoint.proceed();
        Object result = responseEntity.getBody();
        if (result != null) {
            Object localizedDto = localizationUtil.toLocalizedDto(result);
            return ResponseEntity.ok(localizedDto);
        }
        return responseEntity;
    }

    @Around("execution(* ru.utlc.clientmanagementservice.controller.*.create(..)) || execution(* ru.utlc.clientmanagementservice.controller.*.update(..))")
    public Object localizeCreateOrUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseEntity<Response> responseEntity = (ResponseEntity<Response>) joinPoint.proceed();
        Response result = responseEntity.getBody();
        if (result != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            Object localizedDto = localizationUtil.toLocalizedDto(result.resource());
            return ResponseEntity.status(responseEntity.getStatusCode()).body(localizedDto);
        }
        return responseEntity;
    }

}
