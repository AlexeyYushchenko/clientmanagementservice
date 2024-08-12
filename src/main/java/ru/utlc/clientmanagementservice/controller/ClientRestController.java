package ru.utlc.clientmanagementservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.utlc.clientmanagementservice.dto.client.ClientCreateUpdateDto;
import ru.utlc.clientmanagementservice.dto.client.ClientReadDto;
import ru.utlc.clientmanagementservice.exception.ClientCreationException;
import ru.utlc.clientmanagementservice.response.Response;
import ru.utlc.clientmanagementservice.service.ClientService;

import java.net.URI;
import java.util.List;

import static ru.utlc.clientmanagementservice.constants.ApiPaths.CLIENTS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(CLIENTS)
public class ClientRestController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientReadDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll().stream().toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientReadDto> findById(@PathVariable("id") final Integer id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody @Validated final ClientCreateUpdateDto dto,
                                           final BindingResult bindingResult) throws ClientCreationException {

        if (bindingResult.hasFieldErrors()) {
            return handleValidationErrors(bindingResult);
        }

        final var clientReadDto = clientService.create(dto);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientReadDto.id())
                .toUri();

        return ResponseEntity.created(location).body(new Response(clientReadDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") final Integer id,
                                           @RequestBody @Validated final ClientCreateUpdateDto dto,
                                           final BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return handleValidationErrors(bindingResult);
        }

        return clientService.update(id, dto)
                .map(updatedDto -> new ResponseEntity<>(new Response(updatedDto), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Integer id) {
        if (clientService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Response> handleValidationErrors(final BindingResult bindingResult) {
        final List<String> errorMessages = bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final Response response = new Response(errorMessages, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
