package com.xworkz.sweet.runner;

import com.xworkz.sweet.entity.SweetEntity;
import com.xworkz.sweet.repository.SweetRepository;
import com.xworkz.sweet.repository.SweetRepositoryImpl;
import com.xworkz.sweet.service.SweetService;
import com.xworkz.sweet.service.SweetServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class SweetRunner {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-workz");
        SweetRepository repo = new SweetRepositoryImpl(emf);
        SweetService service = new SweetServiceImpl(repo);

        SweetEntity sweet = new SweetEntity();
        sweet.setName("Mysore Pak");
        sweet.setType("Traditional");
        sweet.setCost(250.0);
        sweet.setMadeBy("Sri Krishna Sweets");
        sweet.setIngredients("Ghee, Sugar, Gram Flour");
        sweet.setMadeDate(LocalDate.of(2025, 8, 5));

        boolean saved = service.validateAndSave(sweet);
        System.out.println("Sweet saved: " + saved);

        SweetEntity byName = repo.findByName("Mysore Pak");
        System.out.println("Find by name: " + byName);

        SweetEntity byMaker = repo.findByMadeBy("Sri Krishna Sweets");
        System.out.println("Find by madeBy: " + byMaker);

        SweetEntity byType = repo.findByType("Traditional");
        System.out.println("Find by type: " + byType);

        List<SweetEntity> all = repo.findAll();
        System.out.println("All Sweets: " + all);

        repo.updateNameById(1, "Premium Mysore Pak");
        repo.deleteById(1);

        emf.close();
    }
}
