buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    }
}

group = "m4gshm.springframework"
version = "0.0.1.SNAPSHOT"

repositories.addAll(rootProject.buildscript.repositories)

plugins {
    `java-library`
    `maven-publish`
}

val asSubproject = pluginManager.hasPlugin("io.spring.dependency-management")
if (!asSubproject) apply<io.spring.gradle.dependencymanagement.DependencyManagementPlugin>()

if (!asSubproject) configure<io.spring.gradle.dependencymanagement.internal.dsl.StandardDependencyManagementExtension> {
    dependencies {
        dependency("org.projectlombok:lombok:[1.18,)")
        dependency("org.slf4j:slf4j-api:[1.7,)")
        dependency("org.springframework.boot:spring-boot:[2,)")
        dependency("org.springframework.boot:spring-boot-configuration-processor:[2,)")
        dependency("org.springframework.boot:spring-boot-autoconfigure:[2,)")
        dependency("io.netty:netty-all:[4,)")
        dependency("com.playtika.reactivefeign:feign-reactor-webclient:[2,)")
        dependency("org.springframework.boot:spring-boot-test:[2,)")
        dependency("org.springframework:spring-web:[5,)")
        dependency("org.springframework:spring-webflux:[5,)")
        dependency("org.springframework:spring-test:[5,)")
        dependency("junit:junit:[4,)")
        dependency("io.projectreactor.netty:reactor-netty:[0.9,)")
    }
}

dependencies {

    listOf("annotationProcessor", "testAnnotationProcessor", "compileOnly", "testCompileOnly").forEach {
        add(it, "org.projectlombok:lombok")
    }

    api("org.slf4j:slf4j-api")
    api("org.springframework.boot:spring-boot")
    api("org.springframework.boot:spring-boot-autoconfigure")
    compileOnly("io.netty:netty-all")
    compileOnly("com.playtika.reactivefeign:feign-reactor-webclient")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("junit:junit")
    testImplementation("org.springframework.boot:spring-boot-test")

    testImplementation("org.springframework:spring-web")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.springframework:spring-webflux")
    testImplementation("io.projectreactor.netty:reactor-netty")

}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                properties.put("maven.compiler.target", "${java.targetCompatibility}")
                properties.put("maven.compiler.source", "${java.sourceCompatibility}")
                developers {
                    developer {
                        id.set("buls")
                        name.set("Bulgakov Alexander")
                        email.set("buls@yandex.ru")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/m4gshm/spring-boot-ssl-client.git")
                    developerConnection.set("scm:git:https://github.com/m4gshm/spring-boot-ssl-client.git")
                    url.set("https://github.com/m4gshm/spring-boot-ssl-client")
                }
            }

            versionMapping {
                usage("java-api") {
                    fromResolutionResult()
                }
            }
            from(components["java"])
        }
    }
}
