buildscript {
    repositories.addAll(rootProject.buildscript.repositories)
    val dependencyManagementPluginVer: String by rootProject.extra { "1.0.11.RELEASE" }
    dependencies {
        classpath("io.spring.gradle:dependency-management-plugin:$dependencyManagementPluginVer")
    }
}

group = "m4gshm.springframework.boot"
version = "0.0.1-SNAPSHOT"

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
        dependency("io.netty:netty-all:[4,5)")
        dependency("io.netty:netty-handler:[4,5)")
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

    compileOnly("org.slf4j:slf4j-api")
    compileOnly("org.springframework:spring-web")
    compileOnly("org.springframework:spring-webflux")
    compileOnly("org.springframework.boot:spring-boot")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure")
    compileOnly("io.netty:netty-handler")
    compileOnly("com.playtika.reactivefeign:feign-reactor-webclient") {
        isTransitive = false
    }
    compileOnly("io.projectreactor.netty:reactor-netty")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("junit:junit")
    testImplementation("org.springframework.boot:spring-boot-test")

    testImplementation("org.springframework:spring-web")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.springframework:spring-webflux")
    testImplementation("io.projectreactor.netty:reactor-netty")

}

tasks.compileJava {
    options.apply {
        compilerArgs.add("-Xlint:unchecked")
        isDeprecation = true
        isWarnings = false
    }
}

java {
    withSourcesJar()
}

val buildForJava8: Boolean by rootProject.extra
if (!buildForJava8) {
    java {
        targetCompatibility = JavaVersion.VERSION_1_9
        sourceCompatibility = JavaVersion.VERSION_1_9
        modularity.inferModulePath.set(true)
    }
    sourceSets["main"].java.srcDirs("src/main/module")

    apply {
        from("./automaticModules.gradle.kts")
    }
} else {
    java {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.jar {
        manifest {
            attributes["Automatic-Module-Name"] = "spring.boot.ssl.client"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("java") {
            repositories {
                val mavenTempLocalRepoUrl: String? by rootProject
                if (mavenTempLocalRepoUrl != null) maven {
                    name = "mavenTempLocal"
                    url = uri(mavenTempLocalRepoUrl!!)
                }

                val mavenRepoSnapshotUrl: String? by rootProject
                val mavenRepoReleaseUrl: String? by rootProject
                when {
                    version.endsWith("-SNAPSHOT") -> mavenRepoSnapshotUrl
                    else -> mavenRepoReleaseUrl
                }?.let { repoUrl ->
                    maven {
                        name = "mavenCustom"
                        url = uri(repoUrl)
                        credentials {
                            val mavenRepoUser: String? by rootProject
                            val mavenRepoPassword: String? by rootProject
                            username = mavenRepoUser
                            password = mavenRepoPassword
                        }
                    }
                }
            }

            pom {
                properties.put("maven.compiler.target", "${java.targetCompatibility}")
                properties.put("maven.compiler.source", "${java.sourceCompatibility}")
                developers {
                    developer {
                        id.set("m4gshm")
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
            from(components["java"])
        }
    }
}
