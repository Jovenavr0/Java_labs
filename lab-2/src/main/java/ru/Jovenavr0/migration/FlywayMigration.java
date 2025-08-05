package ru.Jovenavr0.migration;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayMigration {
    public static void migrate(DataSource dataSource) {
        Flyway.configure().dataSource(dataSource).locations("classpath:db/migration").load().migrate();
    }
}
