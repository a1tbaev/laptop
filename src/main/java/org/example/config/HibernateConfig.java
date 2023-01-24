package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.models.Laptop;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfig {
    public static EntityManagerFactory CreateEMF(){
        try{
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5433/laptop");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "postgres");

            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.setProperty(Environment.SHOW_SQL, "true");
            properties.setProperty(Environment.FORMAT_SQL, "true");

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Laptop.class);

            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
