package com.xworkz.temple.runner;

import com.xworkz.temple.entity.TempleEntity;
import com.xworkz.temple.repository.TempleRepository;
import com.xworkz.temple.repository.TempleRepositoryImpl;

public class TempleRunner {
    public static void main(String[] args) {

        TempleEntity temple = new TempleEntity();
        temple.setName("Virupaksha");
        temple.setLocation("Hampi");
        temple.setMainGod("Shiva");
        temple.setFamousFor("Architecture");
        temple.setYearBuilt(1500);
        temple.setEntryFee(20.0);

        TempleRepository repo = new TempleRepositoryImpl();

        // Save
        boolean saved = repo.save(temple);
        System.out.println("Saved: " + saved);

        // Find by Name
        TempleEntity byName = repo.findByName("Virupaksha");
        System.out.println("Find by Name: " + byName);

        // Find by Location
        TempleEntity byLocation = repo.findByLocation("Hampi");
        System.out.println("Find by Location: " + byLocation);

        // Find by Main God
        TempleEntity byGod = repo.findByMainGod("Shiva");
        System.out.println("Find by Main God: " + byGod);

        // Find by Id
        TempleEntity byId = repo.findById(byName.getId());
        System.out.println("Find by ID: " + byId);

        // Update Entry Fee
        boolean updated = repo.updateEntryFeeByName("Virupaksha", 50.0);
        System.out.println("Updated Entry Fee: " + updated);

        // Delete
        boolean deleted = repo.deleteById(byId.getId());
        System.out.println("Deleted: " + deleted);
    }
}
