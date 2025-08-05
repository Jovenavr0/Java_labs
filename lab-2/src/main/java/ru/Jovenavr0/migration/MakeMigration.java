package ru.Jovenavr0.migration;

import com.zaxxer.hikari.HikariDataSource;


public class MakeMigration {

    public void Migrate() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:postgresql://localhost:6432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        FlywayMigration.migrate(dataSource);
    }

}
