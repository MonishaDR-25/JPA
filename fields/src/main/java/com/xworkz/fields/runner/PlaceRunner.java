package com.xworkz.fields.runner;

import com.xworkz.fields.entity.PlaceEntity;
import com.xworkz.fields.repository.PlaceRepoImpl;
import com.xworkz.fields.repository.PlaceRepository;
import com.xworkz.fields.service.PlaceService;
import com.xworkz.fields.service.PlaceServiceImpl;

public class PlaceRunner {
    public static void main(String[] args) {
        PlaceEntity entity = new PlaceEntity();
        entity.setName("Hampi");
        entity.setCity("Hospet");
        entity.setState("Karnataka");
        entity.setCountry("India");
        entity.setFamousFor("Ruins");
        entity.setPincode(583239);

        PlaceRepository repo = new PlaceRepoImpl();
        PlaceService service = new PlaceServiceImpl(repo);

        boolean saved = service.validateAndSave(entity);
        System.out.println("Saved: " + saved);

        System.out.println("Find by name: " + repo.findByName("Hampi"));
        System.out.println("Find by city: " + repo.findByCity("Hospet"));
        System.out.println("Find by famous for: " + repo.findByFamousFor("Ruins"));

        boolean updated = repo.updateCityById("Ballari", 1);
        System.out.println("Updated: " + updated);

        boolean deleted = repo.deleteById(1);
        System.out.println("Deleted: " + deleted);
    }
}
