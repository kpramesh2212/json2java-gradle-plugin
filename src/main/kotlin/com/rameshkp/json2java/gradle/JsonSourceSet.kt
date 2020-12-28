package com.rameshkp.json2java.gradle

import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.model.ObjectFactory
import org.gradle.util.ConfigureUtil

@Suppress("UnstableApiUsage")
open class JsonSourceSet(private val objectFactory: ObjectFactory) {
    val schema: SourceDirectorySet = objectFactory.sourceDirectorySet("schema", "json schema sources")

    init {
        schema.filter.include("**/*.json", "**/*.yaml", "**/*.yml", "**/*.jsonschema")
    }

    fun schema(action: Action<in SourceDirectorySet>) {
        action.execute(schema)
    }

    fun schema(closure: Closure<*>) {
        ConfigureUtil.configure(closure, schema)
    }
}