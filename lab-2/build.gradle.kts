plugins {
    id("java")
}

group = "ru.Jovenavr0"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:4.5.1")
    testImplementation("org.mockito:mockito-junit-jupiter:4.5.1")


    implementation("com.zaxxer:HikariCP:5.1.0")

    implementation("org.flywaydb:flyway-core:10.7.2")
    implementation("org.flywaydb:flyway-database-postgresql:10.7.2")

    implementation("org.slf4j:slf4j-simple:2.0.12")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.hibernate.orm:hibernate-core:6.6.12.Final")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }

    failFast = false

    filter {
        includeTestsMatching("*Test*")
    }
}

tasks.register<JavaExec>("RunProgram") {
    group = "application"
    description = "Run main programs"
    mainClass.set("ru.Jovenavr0.Main")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}