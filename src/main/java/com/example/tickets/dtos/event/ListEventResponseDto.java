package com.example.tickets.dtos.event;

import com.example.tickets.domain.enums.EventStatus;
import com.example.tickets.dtos.ticketType.ListEventTicketTypeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListEventResponseDto {

    private UUID id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String venue;
    private EventStatus status;
    private LocalDateTime salesStartTime;
    private LocalDateTime salesEndTime;
    private List<ListEventTicketTypeResponseDto> ticketTypes = new ArrayList<>();
}
