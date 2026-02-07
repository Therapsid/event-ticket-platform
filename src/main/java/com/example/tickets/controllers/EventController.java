package com.example.tickets.controllers;

import com.example.tickets.domain.entities.Event;
import com.example.tickets.dtos.event.CreateEventRequestDto;
import com.example.tickets.dtos.event.CreateEventResponseDto;
import com.example.tickets.dtos.event.ListEventResponseDto;
import com.example.tickets.mappers.EventMapper;
import com.example.tickets.services.EventService;
import com.example.tickets.services.model.CreateEventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Operations related to event management by organizers")
@SecurityRequirement(name = "bearerAuth")
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    @Operation(
            summary = "Create a new event",
            description = "Creates a new event for the authenticated organizer. Requires name, date, and venue details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Event created successfully",
            content = @Content(schema = @Schema(implementation = CreateEventResponseDto.class))
    )
    @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)")
    @ApiResponse(responseCode = "401", description = "User is not authorized")
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto event) {

        CreateEventRequest createEventRequest = eventMapper.fromDto(event);
        UUID userId = parseUserId(jwt);

        Event createEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "List events",
            description = "Get a paginated list of events created by the authenticated organizer."
    )
    @ApiResponse(responseCode = "200", description = "List of events retrieved successfully")
    public ResponseEntity<Page<ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ){
        UUID userId = parseUserId(jwt);
        Page<Event> events = eventService.listEventsForOrganizer(userId, pageable);
        return ResponseEntity.ok(
                events.map(eventMapper::toListEventResponseDto)
        );
    }

    private UUID parseUserId(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }
}
