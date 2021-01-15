buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    }
}

repositories.addAll(rootProject.buildscript.repositories)

plugins {
    java
    `java-library`
}

val asSubproject = pluginManager.hasPlugin("io.spring.dependency-management")
if (!asSubproject) apply<io.spring.gradle.dependencymanagement.DependencyManagementPlugin>()

if (!asSubproject) configure<io.spring.gradle.dependencymanagement.internal.dsl.StandardDependencyManagementExtension> {
    dependencies {
        dependency("org.projectlombok:lombok:1.18.+")
        dependency("org.slf4j:slf4j-api:1.7.+")
        dependency("org.springframework.boot:spring-boot:2.+")
        dependency("org.springframework.boot:spring-boot-configuration-processor:2.+")
    }
}

dependencies {

    listOf("annotationProcessor", "testAnnotationProcessor", "compileOnly", "testCompileOnly").forEach {
        add(it, "org.projectlombok:lombok")
    }

    api("org.slf4j:slf4j-api")
    api("org.springframework.boot:spring-boot")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
