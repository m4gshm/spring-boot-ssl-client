buildscript {
    repositories.addAll(rootProject.buildscript.repositories)
    dependencies {
        classpath("de.jjohannes.gradle:extra-java-module-info:0.5")
    }
}

apply {
    plugin(de.jjohannes.gradle.javamodules.ExtraModuleInfoPlugin::class.java)
}

configure<de.jjohannes.gradle.javamodules.ExtraModuleInfoPluginExtension> {
    automaticModule("hamcrest-core-1.3.jar", "hamcrest.core")
    automaticModule("feign-reactor-core-2.0.26.jar", "feign.reactor.core")
    automaticModule("feign-reactor-webclient-2.0.26.jar", "feign.reactor.webclient")
}