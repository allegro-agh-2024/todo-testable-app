plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20"

    id("org.springframework.boot") version "2.7.4"
    id("nebula.integtest") version "9.6.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform(kotlin("bom")))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.7.4"))
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation(platform("org.testcontainers:testcontainers-bom:1.17.5"))
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")

    integTestImplementation("org.springframework.boot:spring-boot-starter-test")
    integTestImplementation("org.springframework.security:spring-security-test")
    integTestImplementation("org.testcontainers:mongodb")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}
