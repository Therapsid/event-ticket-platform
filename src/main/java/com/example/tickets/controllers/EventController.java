package com.example.tickets.controllers;

import com.example.tickets.domain.entities.Event;
import com.example.tickets.dtos.event.CreateEventRequestDto;
import com.example.tickets.dtos.event.CreateEventResponseDto;
import com.example.tickets.mappers.EventMapper;
import com.example.tickets.services.EventService;
import com.example.tickets.services.model.CreateEventRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto event) {
        CreateEventRequest createEventRequest = eventMapper.fromDto(event);
        UUID userId = UUID.fromString(jwt.getSubject());

        Event createEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

}
