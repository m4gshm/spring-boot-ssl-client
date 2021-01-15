plugins {
    java
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
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
