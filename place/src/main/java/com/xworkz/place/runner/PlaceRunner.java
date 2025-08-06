package com.xworkz.place.runner;

import com.xworkz.place.entity.PlaceEntity;
import com.xworkz.place.repository.PlaceRepository;
import com.xworkz.place.repository.PlaceRepositoryImpl;
import com.xworkz.place.service.PlaceService;
import com.xworkz.place.service.PlaceServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class PlaceRunner {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-workz");
        PlaceRepository repo = new PlaceRepositoryImpl(emf);
        PlaceService service = new PlaceServiceImpl(repo);

        PlaceEntity entity = new PlaceEntity();
        entity.setName("Mysore Palace");
        entity.setCity("Mysore");
        entity.setState("Karnataka");
        entity.setCountry("India");
        entity.setVisitedDate(LocalDate.of(2024, 12, 5));
        entity.setFamousFor("Architecture");

        boolean saved = service.validateAndSave(entity);
        System.out.println("Saved: " + saved);

        PlaceEntity byName = repo.findByName("Mysore Palace");
        System.out.println("Find by Name: " + byName);

        PlaceEntity byCity = repo.findByCity("Mysore");
        System.out.println("Find by City: " + byCity);

        PlaceEntity byFamous = repo.findByFamousFor("Architecture");
        System.out.println("Find by Famous For: " + byFamous);

        List<PlaceEntity> all = repo.findAll();
        System.out.println("All Places: " + all);

        repo.updateNameById(1, "Mysore Palace Updated");
        repo.deleteById(1);

        emf.close();
    }
}
