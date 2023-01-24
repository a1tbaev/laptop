package org.example;

import org.example.config.HibernateConfig;
import org.example.enums.OperationSystem;
import org.example.models.Laptop;
import org.example.service.LaptopService;
import org.example.service.LaptopServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LaptopService laptopService = new LaptopServiceImpl();
        //System.out.println(laptopService.saveProgrammer(new Laptop("Apple", OperationSystem.IOS, 128, 2000, LocalDate.of(2020, 1, 1))));

        Laptop laptop = new Laptop("Apple", OperationSystem.IOS, 256, 3000, LocalDate.of(2020, 3, 30));
        Laptop laptop1 = new Laptop("Asus", OperationSystem.WIDOWS, 256, 2000, LocalDate.of(2020, 3, 30));
        Laptop laptop2 = new Laptop("Acer", OperationSystem.WIDOWS, 256, 1500, LocalDate.of(2020, 3, 30));
        List<Laptop> laptops = new ArrayList<>(List.of(laptop, laptop1, laptop2));

        //laptopService.saveAll(laptops).forEach(System.out::println);
        //System.out.println(laptopService.deleteById(1L));
        //laptopService.deleteAll();
        System.out.println(laptopService.findAll());
        System.out.println(laptopService.update(1L, laptop2));
        System.out.println(laptopService.groupBy());
        System.out.println(laptopService.sortByDifferentColumn("brand", "asc"));

    }
}
