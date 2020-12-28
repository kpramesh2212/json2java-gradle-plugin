plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.jsonschema2pojo", name = "jsonschema2pojo-core", version = "1.0.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

gradlePlugin {
    plugins {
        create("json2java-gradle-plugin") {
            id = "com.rameshkp.json2java-gradle-plugin"
            displayName = "Json2Java Gradle Plugin"
            description = "A gradle plugin to convert json schemas into POJOs"
            implementationClass = "com.rameshkp.json2java.gradle.Json2JavaGradlePlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "local"
            url = uri("$buildDir/repo")
        }
    }
}