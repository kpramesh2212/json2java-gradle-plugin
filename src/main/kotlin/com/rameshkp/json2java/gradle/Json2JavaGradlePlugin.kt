package com.rameshkp.json2java.gradle

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.get

@Suppress("UNCHECKED_CAST")
open class Json2JavaGradlePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        val schemaSourceSet = configureJsonSourceSet(project)
        // create the extension and the task
        val extension = project.extensions.create("json2Java", Json2JavaGradleExtension::class.java, project.objects)
        project.tasks.register("json2Java", Json2JavaConverterTask::class.java, extension, schemaSourceSet)
        configureOutputs(project, extension)
    }

    private fun configureJsonSourceSet(project: Project): JsonSourceSet {
        project.pluginManager.apply(JavaBasePlugin::class.java)
        val sourceSetContainer = project.extensions["sourceSets"] as NamedDomainObjectContainer<SourceSet>
        val mainJsonSourceSet = sourceSetContainer.create("json")
        mainJsonSourceSet.extensions.create("schema", JsonSourceSet::class.java, project.objects)
        val schemaSourceSet = mainJsonSourceSet.extensions["schema"] as JsonSourceSet
        schemaSourceSet.schema.srcDir("src/json/schema")
        mainJsonSourceSet.allJava.setSrcDirs(schemaSourceSet.schema.srcDirs)
        mainJsonSourceSet.allSource.setSrcDirs(schemaSourceSet.schema.srcDirs)
        mainJsonSourceSet.allSource.source(schemaSourceSet.schema)
        mainJsonSourceSet.allJava.source(schemaSourceSet.schema)
        return schemaSourceSet
    }

    private fun configureOutputs(project: Project, extension: Json2JavaGradleExtension) {
        val sourceSetContainer = project.extensions["sourceSets"] as NamedDomainObjectContainer<SourceSet>
        project.afterEvaluate {
            // Configure the output
            if (!extension.outputDir.isPresent) {
                extension.outputDir.set(file("${project.buildDir}/generated-sources/json2java"))
            }
            if (project.pluginManager.hasPlugin("java")) {
                sourceSetContainer["main"].java.srcDir(extension.outputDir)
            }
        }
    }

}