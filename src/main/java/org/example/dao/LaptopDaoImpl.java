package org.example.dao;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.enums.OperationSystem;
import org.example.models.Laptop;
import org.hibernate.Session;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LaptopDaoImpl implements LaptopDao {
    private EntityManagerFactory entityManagerFactory = HibernateConfig.CreateEMF();
    @Override
    public Laptop saveProgrammer(Laptop laptop) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(laptop);
            entityManager.getTransaction().commit();
            entityManager.close();
            return laptop;
        }catch (EntityExistsException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Laptop> saveAll(List<Laptop> laptops) {
        for (Laptop laptop : laptops) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(laptop);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
        return laptops;
    }

    @Override
    public Laptop deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Laptop laptop = entityManager.createQuery("select l from Laptop l where l.id = ?1", Laptop.class)
                .setParameter(1, id).getSingleResult();
        entityManager.remove(laptop);
        entityManager.getTransaction().commit();
        entityManager.close();
        return laptop;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Laptop> laptops = entityManager.createQuery("select l from Laptop l ",Laptop.class).getResultList();
        for (Laptop laptop : laptops) {
            entityManager.remove(laptop);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("Deleted all!");
    }

    @Override
    public List<Laptop> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Laptop> laptops = entityManager.createQuery("select l from Laptop l ", Laptop.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return laptops;

    }

    @Override
    public Laptop update(Long id, Laptop laptop) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Laptop l = entityManager.getReference(Laptop.class,id);
        l.setBrand(laptop.getBrand());
        l.setPrice(laptop.getPrice());
        l.setDateOfIssue(laptop.getDateOfIssue());
        l.setOperationSystem(laptop.getOperationSystem());
        entityManager.getTransaction().commit();
        entityManager.close();
        return l;
    }

    @Override
    public Map<OperationSystem, List<Laptop>> groupBy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Map<OperationSystem, List<Laptop>> collect = entityManager.createQuery("select l from Laptop l",Laptop.class).getResultStream()
                .collect(Collectors.groupingBy(Laptop::getOperationSystem));
        entityManager.getTransaction().commit();
        entityManager.close();
        return collect;
    }

    @Override
    public List<Laptop> sortByDifferentColumn(String column, String ascOrDesc) {
        List<Laptop> laptops = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if(column.equals("brand")){
            if(ascOrDesc.equals("asc")){
                laptops = entityManager.createQuery("select l from Laptop l order by brand", Laptop.class).getResultList();
            } else if (ascOrDesc.equals("desc")) {
                laptops = entityManager.createQuery("select l from Laptop l order by brand desc ",Laptop.class).getResultList();
            }
        } else if (column.equals("operation_system")) {
            if(ascOrDesc.equals("asc")){
                laptops = entityManager.createQuery("select l from Laptop l order by operationSystem",Laptop.class).getResultList();
            } else if (ascOrDesc.equals("desc")) {
                laptops = entityManager.createQuery("select l from Laptop l order by operationSystem desc ",Laptop.class).getResultList();
            }
        } else if (column.equals("memory")) {
            if(ascOrDesc.equals("asc")){
                laptops = entityManager.createQuery("select l from Laptop l order by memory",Laptop.class).getResultList();
            } else if (ascOrDesc.equals("desc")) {
                laptops = entityManager.createQuery("select l from Laptop l order by memory desc ",Laptop.class).getResultList();
            }
        } else if (column.equals("price")) {
            if(ascOrDesc.equals("asc")){
                laptops = entityManager.createQuery("select l from Laptop l order by price",Laptop.class).getResultList();
            }else if(ascOrDesc.equals("desc")){
                laptops = entityManager.createQuery("select l from Laptop l order by price desc ",Laptop.class).getResultList();
            }
        }else if(column.equals("date_of_issue")){
            if(ascOrDesc.equals("asc")){
                laptops = entityManager.createQuery("select l from Laptop l order by dateOfIssue",Laptop.class).getResultList();
            } else if (ascOrDesc.equals("desc")) {
                laptops = entityManager.createQuery("select l from Laptop l order by dateOfIssue desc ",Laptop.class).getResultList();
            }
        }
        return laptops;
    }

}
