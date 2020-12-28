package com.rameshkp.json2java.gradle

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.get

@Suppress("UNCHECKED_CAST", "UnstableApiUsage", "unused")
open class Json2JavaGradlePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        val schemaSourceSet = configureJsonSourceSet(project)
        // create the extension and the task
        val extension = project.extensions.create("json2Java", Json2JavaGradleExtension::class.java, project.objects)
        val json2JavaTask = project.tasks.register("json2Java", Json2JavaConverterTask::class.java, extension, schemaSourceSet)
        configureOutputs(project, extension, json2JavaTask)
    }

    private fun configureJsonSourceSet(project: Project): SourceDirectorySet {
        project.pluginManager.apply(JavaBasePlugin::class.java)
        val sourceSetContainer = project.extensions["sourceSets"] as NamedDomainObjectContainer<SourceSet>
        val mainJson2JavaSourceSet = sourceSetContainer.create("json2java")
        val schemaSourceDirectorySet = project.objects.sourceDirectorySet("schema", "json2java schema sources")
        mainJson2JavaSourceSet.extensions.add("schema", schemaSourceDirectorySet)

        schemaSourceDirectorySet.srcDir("src/json2java/schema")
        mainJson2JavaSourceSet.allJava.setSrcDirs(schemaSourceDirectorySet.srcDirs)
        mainJson2JavaSourceSet.allSource.setSrcDirs(schemaSourceDirectorySet.srcDirs)
        mainJson2JavaSourceSet.allSource.source(schemaSourceDirectorySet)
        mainJson2JavaSourceSet.allJava.source(schemaSourceDirectorySet)
        return schemaSourceDirectorySet
    }

    private fun configureOutputs(project: Project, extension: Json2JavaGradleExtension, json2JavaConverterTask: TaskProvider<Json2JavaConverterTask>) {
        val sourceSetContainer = project.extensions["sourceSets"] as NamedDomainObjectContainer<SourceSet>
        project.afterEvaluate {
            // Configure the output
            if (!extension.outputDir.isPresent) {
                extension.outputDir.set(file("${project.buildDir}/generated-sources/json2java"))
            }
            if (project.pluginManager.hasPlugin("java")) {
                sourceSetContainer["main"].java.srcDir(extension.outputDir)
                tasks.named("compileJava") {
                    dependsOn(json2JavaConverterTask)
                }
            }
        }
    }

}