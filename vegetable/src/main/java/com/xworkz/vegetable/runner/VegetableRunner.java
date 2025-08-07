package com.xworkz.vegetable.runner;

import com.xworkz.vegetable.entity.VegetableEntity;
import com.xworkz.vegetable.repository.VegetableRepository;
import com.xworkz.vegetable.repository.VegetableRepositoryImpl;
import com.xworkz.vegetable.service.VegetableService;
import com.xworkz.vegetable.service.VegetableServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class VegetableRunner {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("x-workz");
        VegetableRepository repo = new VegetableRepositoryImpl(emf);
        VegetableService service = new VegetableServiceImpl(repo);

        VegetableEntity veg = new VegetableEntity();
        veg.setName("Carrot");
        veg.setType("Root");
        veg.setColor("Orange");
        veg.setOrigin("Ooty");
        veg.setCost(35.0);
        veg.setArrivalDate(LocalDate.of(2025, 7, 10));

        boolean saved = service.validateAndSave(veg);
        System.out.println("Vegetable saved: " + saved);

        VegetableEntity byName = repo.findByName("Carrot");
        System.out.println("By Name: " + byName);

        VegetableEntity byColor = repo.findByColor("Orange");
        System.out.println("By Color: " + byColor);

        VegetableEntity byType = repo.findByType("Root");
        System.out.println("By Type: " + byType);

        List<String> findName= repo.getVegetableName();
        System.out.println(findName);

        List<Double> findCost= repo.getVegetableCost();
        System.out.println(findCost);

        List<Object> findDate= repo.getVegetableArrivalDate();
        System.out.println(findDate);

        List<Object[]> ref2=repo.getVegetableColorAndOrigin();
        ref2.stream().map(r->"color:"+r[0]+"origin:"+r[1]).forEach(System.out::println);


        List<VegetableEntity> all = repo.findAll();
        System.out.println("All Vegetables: " + all);

        repo.updateNameById(1, "Carrot Premium");
        repo.deleteById(1);

        emf.close();
    }
}
