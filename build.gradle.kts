plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"

    id("org.springframework.boot") version "2.7.5"
    id("nebula.integtest") version "9.6.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform(kotlin("bom")))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.7.5"))
    implementation(platform("org.junit:junit-bom:5.9.1"))

    constraints {
        implementation("org.springdoc:springdoc-openapi-ui:1.6.12")
        implementation("io.strikt:strikt-core:0.34.1")
        implementation("io.mockk:mockk:1.13.2")
        implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.5.2")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springdoc:springdoc-openapi-ui")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.strikt:strikt-core")
    testImplementation("io.mockk:mockk")

    integTestImplementation("org.springframework.boot:spring-boot-starter-test")
    integTestImplementation("org.springframework.security:spring-security-test")
    integTestImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}
