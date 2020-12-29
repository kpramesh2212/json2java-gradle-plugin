plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("net.researchgate.release") version "2.8.1"
    id("com.gradle.plugin-publish") version "0.12.0"
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

pluginBundle {
    website = "https://github.com/kpramesh2212/json2java-gradle-plugin"
    vcsUrl = "https://github.com/kpramesh2212/json2java-gradle-plugin.git"
    tags = listOf("json2java", "jsonschema2java", "json2pojo", "jsonschema2pojo", "yaml2java", "yaml2pojo", "pojo-generator")
}

val afterReleaseBuild by tasks.existing

afterEvaluate {
  afterReleaseBuild {
      dependsOn(tasks.named("publish"))
      dependsOn(tasks.named("publishPlugins"))
  }
}

release {
    (getProperty("git") as net.researchgate.release.GitAdapter.GitConfig).apply {
        requireBranch = "main"
    }
}