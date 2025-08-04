package com.xworkz.fields.runner;

import com.xworkz.fields.entity.EventEntity;
import com.xworkz.fields.repository.EventRepoImpl;
import com.xworkz.fields.repository.EventRepository;
import com.xworkz.fields.service.EventService;
import com.xworkz.fields.service.EventServiceImpl;

import java.time.LocalDate;

public class EventRunner {
    public static void main(String[] args) {
        EventEntity entity = new EventEntity();
        entity.setName("Music Fest");
        entity.setLocation("Bangalore");
        entity.setBudget(500000.0);
        entity.setOrganiser("XWorkz Events");
        entity.setDate(LocalDate.of(2025, 8, 15));
        entity.setSponsor("FFF Pvt Ltd");
        entity.setCapacity(1000);

        EventService service = new EventServiceImpl();
        boolean saved = service.validateAndSave(entity);
        System.out.println("Saved by service: " + saved);

        EventRepository repo = new EventRepoImpl();

        // Find by name
        EventEntity byName = repo.findByName("Music Fest");
        System.out.println("Event by name: " + byName);

        // Find by location
        EventEntity byLocation = repo.findByLocation("Bangalore");
        System.out.println("Event by location: " + byLocation);

        // Find by budget
        EventEntity byBudget = repo.findByBudget(500000.0);
        System.out.println("Event by budget: " + byBudget);

        // Update organiser
        boolean updated = repo.updateOrganiserById(byName.getId(), "EventX Pvt Ltd");
        System.out.println("Update organiser: " + updated);

        // Delete by ID
        boolean deleted = repo.deleteById(byName.getId());
        System.out.println("Deleted event: " + deleted);
    }
}
