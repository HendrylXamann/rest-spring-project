plugins {
	java
	id("org.springframework.boot") version "3.2.7"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "library-rest-spring-boot"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude(group = "org.mockito", module = "mockito-core")
	}
	testImplementation("org.mockito:mockito-core:4.3.1")
	testImplementation("org.mockito:mockito-junit-jupiter:4.0.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("javax.persistence:javax.persistence-api:2.2")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3")

}

tasks.withType<JavaExec> {
	environment("POSTGRES_USER", System.getenv("POSTGRES_USER") ?: "postgres")
	environment("POSTGRES_PASSWORD", System.getenv("POSTGRES_PASSWORD") ?: "postgres123")
	environment("POSTGRES_DB", System.getenv("POSTGRES_DB") ?: "librarydb")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
