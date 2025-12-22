package com.example.tickets.services;

import com.example.tickets.domain.entities.Event;
import com.example.tickets.services.model.CreateEventRequest;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest event);

}
