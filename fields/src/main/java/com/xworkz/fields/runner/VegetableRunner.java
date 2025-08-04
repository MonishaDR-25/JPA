package com.xworkz.fields.runner;

import com.xworkz.fields.entity.VegetableEntity;
import com.xworkz.fields.repository.VegetableRepoImpl;
import com.xworkz.fields.repository.VegetableRepository;

import java.time.LocalDate;

public class VegetableRunner {
    public static void main(String[] args) {
        VegetableRepository repo = new VegetableRepoImpl();

        // Save vegetable
        VegetableEntity entity = new VegetableEntity();
        entity.setName("Carrot");
        entity.setColor("Orange");
        entity.setWeight(1.5);
        entity.setType("Root");
        entity.setCost(200.3);
        entity.setDate(LocalDate.of(2025, 7, 25));
        repo.save(entity);

        // Find by name
        VegetableEntity byName = repo.findByName("Carrot");
        System.out.println("Vegetable by name: " + byName);

        // Find by color
        VegetableEntity byColor = repo.findByColor("Orange");
        System.out.println("Vegetable by color: " + byColor);

        // Find by weight
        VegetableEntity byType = repo.findByType("Root");
        System.out.println("Vegetable by weight: " + byType);

        // Update supplier
        boolean updated = repo.updateCostById(byName.getId(), 200.3);
        System.out.println("Update supplier: " + updated);

        // Delete by ID
        boolean deleted = repo.deleteById(byName.getId());
        System.out.println("Deleted vegetable: " + deleted);
    }
}
