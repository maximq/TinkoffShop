plugins {
    id("java")
    id("org.springframework.boot") version "2.6.14"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:42.2.20")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.swagger.core.v3:swagger-annotations:2.1.6")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.2")
    compileOnly ("org.projectlombok:lombok:1.18.26")
    annotationProcessor ("org.projectlombok:lombok:1.18.26")
    testCompileOnly ("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.26")
    testImplementation("io.rest-assured:rest-assured:4.3.3")
}

tasks.test {
    useJUnitPlatform()
}
