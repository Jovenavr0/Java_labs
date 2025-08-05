package ru.Jovenavr0.configMaster;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Configuration;
import ru.Jovenavr0.entity.Owner;
import ru.Jovenavr0.entity.Pet;
import ru.Jovenavr0.entity.PetColor;

import java.util.Properties;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {

            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:6432/postgres");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "postgres");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Owner.class);
            configuration.addAnnotatedClass(Pet.class);
            configuration.addAnnotatedClass(PetColor.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e){
            throw new RuntimeException("Error getting Hibernate SessionFactory", e);
        }

        return sessionFactory;
    }
}
