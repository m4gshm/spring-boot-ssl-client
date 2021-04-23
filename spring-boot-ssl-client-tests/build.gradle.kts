repositories.addAll(rootProject.buildscript.repositories)

val libraryDepenedencySrc: String? = rootProject.properties["libraryDepenedencySrc"] as String?
val libraryVersion: String? = rootProject.properties["libraryVersion"] as String?

repositories {
    if (libraryDepenedencySrc == "maven-test") {
        maven("http://m4gshm.github.io/maven2/")
    }
}

plugins {
    java
}

dependencies {

    testImplementation(when (libraryDepenedencySrc) {
        in listOf("maven", "maven-test") -> "m4gshm.springframework.boot:spring-boot-ssl-client:${libraryVersion ?: "0.0.1"}"
        else -> project(":spring-boot-ssl-client")
    })

    listOf("annotationProcessor", "testAnnotationProcessor", "compileOnly", "testCompileOnly").forEach {
        add(it, "org.projectlombok:lombok:1.18.16")
    }

    testImplementation("org.slf4j:slf4j-simple:2.0.0-alpha1")
    testImplementation("junit:junit:4.13.1")
    testImplementation("io.projectreactor.netty:reactor-netty-http:1.0.3")
    testImplementation("io.netty:netty-handler:4.1.58.Final")
    testImplementation("org.springframework:spring-test:5.3.3")
    testImplementation("org.springframework:spring-web:5.3.3")
    testImplementation("org.springframework.boot:spring-boot-autoconfigure:2.4.2")
    testImplementation("org.springframework.boot:spring-boot-test:2.4.2")

}

val buildForJava8: Boolean by rootProject.extra
if (!buildForJava8) {
    java {
        targetCompatibility = JavaVersion.VERSION_1_9
        sourceCompatibility = JavaVersion.VERSION_1_9
        modularity.inferModulePath.set(true)
    }
    sourceSets["test"].java.srcDirs("src/test/module")
} else {
    java {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

val copyToModelClasses = tasks.create<Copy>("copyToModelClasses") {
    from(tasks.processTestResources.get().source)
    destinationDir = tasks.compileTestJava.get().destinationDir
}

tasks.compileTestJava {
    dependsOn(copyToModelClasses)
}



