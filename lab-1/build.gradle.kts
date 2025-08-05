plugins {
    id("java")
}

group = "ru.Jovenavr0"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("RunProgram") {
    group = "application"
    description = "Run main programs"
    mainClass.set("ru.Jovenavr0.Main")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    dependsOn(tasks.javadoc)
}

tasks.javadoc{
    options.encoding = "UTF-8"
}