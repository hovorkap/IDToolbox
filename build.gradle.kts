import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
    id ("idea")
	kotlin("plugin.spring") version "1.4.10"
}

group = "hovorkap"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springdoc:springdoc-openapi-ui:1.4.5")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.4.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.apache.commons:commons-csv:1.5")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.9")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
