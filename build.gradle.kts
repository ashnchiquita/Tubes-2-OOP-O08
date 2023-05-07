plugins {
    id("java")
    id("io.freefair.lombok") version "8.0.1"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.0")
    implementation("org.jetbrains:annotations:24.0.0")
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("mysql:mysql-connector-java:5.1.32")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    enabled = true
    isZip64 = true
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveFileName.set("project.jar")
    from(sourceSets.main.get().output)
    dependsOn(configurations.compileClasspath)
    from({
        configurations.compileClasspath.get().filter {
            it.name.endsWith("jar")
        }.map { zipTree(it) }
    }) {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
    manifest {
        attributes["Main-Class"] = "model.TesJSON"
    }
}
application {
    mainClass.set("boundary.Tester")
}