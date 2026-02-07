package com.example.tickets.mappers;

import com.example.tickets.domain.entities.Event;
import com.example.tickets.domain.entities.TicketType;
import com.example.tickets.dtos.event.CreateEventRequestDto;
import com.example.tickets.dtos.event.CreateEventResponseDto;
import com.example.tickets.dtos.event.ListEventResponseDto;
import com.example.tickets.dtos.ticketType.CreateTicketTypeRequestDto;
import com.example.tickets.dtos.ticketType.ListEventTicketTypeResponseDto;
import com.example.tickets.services.model.CreateEventRequest;
import com.example.tickets.services.model.CreateTicketTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);
}
