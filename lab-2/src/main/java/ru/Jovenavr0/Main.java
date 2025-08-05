package ru.Jovenavr0;

import org.hibernate.SessionFactory;
import ru.Jovenavr0.configMaster.HibernateConfig;
import ru.Jovenavr0.controller.ConsoleInterface;
import ru.Jovenavr0.dao.OwnerDao;
import ru.Jovenavr0.dao.PetDao;
import ru.Jovenavr0.dao.OwnerDaoImp;
import ru.Jovenavr0.dao.PetDaoImp;
import ru.Jovenavr0.migration.MakeMigration;
import ru.Jovenavr0.service.OwnerService;
import ru.Jovenavr0.service.PetService;


public class Main {
    public static void main(String[] args) {

        MakeMigration makeMigration = new MakeMigration();
        makeMigration.Migrate();

        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

        PetDao petDao = new PetDaoImp(sessionFactory);
        OwnerDao ownerDao = new OwnerDaoImp(sessionFactory);
        OwnerService ownerService = new OwnerService(ownerDao);
        PetService petService = new PetService(petDao);
        ConsoleInterface console = new ConsoleInterface(ownerService, petService);

        console.start();

        sessionFactory.close();

    }
}