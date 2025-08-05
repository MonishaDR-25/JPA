package com.xworkz.event.runner;

import com.xworkz.event.entity.EventEntity;
import com.xworkz.event.repository.EventRepoImpl;
import com.xworkz.event.repository.EventRepository;
import com.xworkz.event.service.EventService;
import com.xworkz.event.service.EventServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class EventRunner {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-workz");
        EventRepository eventRepository = new EventRepoImpl(emf);
        EventService eventService = new EventServiceImpl(eventRepository);

        EventEntity eventEntity = new EventEntity();
        eventEntity.setName("Engagement");
        eventEntity.setLocation("Bangalore");
        eventEntity.setBudget(400000.0);
        eventEntity.setOrganiser("XWorkz Events");
        eventEntity.setDate(LocalDate.of(2025, 4, 23));
        eventEntity.setSponsor("Ram");
        eventEntity.setCapacity(500);

        boolean saved = eventService.validateSave(eventEntity);
        System.out.println("Event entity saved: " + saved);

        EventEntity get = eventRepository.getEventByName("Engagement");
        System.out.println("Get by name: " + get);

        EventEntity getLocation = eventRepository.getEventByLocation("Bangalore");
        System.out.println("Get by location: " + getLocation);

        EventEntity getBudget = eventRepository.getEventByBudget(400000.0);
        System.out.println("Get by budget: " + getBudget);

        List<EventEntity> ref = eventRepository.getEvent();
        System.out.println("All Events: " + ref);

        EventEntity read = eventRepository.readById(2);
        System.out.println("Read by ID 2: " + read);

        System.out.println("Update operation");
        eventRepository.updateEventById(2, "Engagement Updated");

        System.out.println("Delete operation");
        eventRepository.deleteById(2);

        emf.close();
    }
}
