package com.example.tickets.dtos.event;

import com.example.tickets.domain.enums.EventStatus;
import com.example.tickets.dtos.ticketType.CreateTicketTypeRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {

    @NotBlank(message = "Event name is required")
    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NotBlank(message = "Venue information is required")
    private String venue;

    private LocalDateTime salesStartTime;

    private LocalDateTime salesEndTime;

    @NotNull(message = "Event status must be provided")
    private EventStatus status;

    @NotEmpty(message = "At least one ticket type is required")
    @Valid
    private List<CreateTicketTypeRequestDto> ticketTypes;



}
