package com.rameshkp.json2java.gradle

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.sun.codemodel.JCodeModel
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.file.Directory
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.jsonschema2pojo.*
import org.jsonschema2pojo.rules.RuleFactory
import java.io.File
import javax.inject.Inject

open class Json2JavaConverterTask @Inject constructor(private val extension: Json2JavaGradleExtension, private val sourceSet: SourceDirectorySet): DefaultTask() {

    @get:InputFiles
    val inputFiles: Set<File> get() {
        val fileTree = extension.input.asFileTree.matching {
            setIncludes(sourceSet.filter.includes)
            setExcludes(sourceSet.filter.excludes)
        }
        return sourceSet.files + fileTree.files
    }

    @OutputDirectory
    val outputDir: Provider<Directory> = extension.outputDir

    init {
        group = "Json2Java"
        description = "Converts json schema files pojo's"
    }

    @TaskAction
    fun execute() {
        // create our Generate config
        val generationConfig = Json2JavaGenerationConfig(extension)
        val schemaMapper = SchemaMapper(createRuleFactory(generationConfig), createSchemaGenerator(generationConfig))

        inputFiles.forEach {
            val jCodeModel = JCodeModel()
            schemaMapper.generate(jCodeModel, it.name, generationConfig.targetPackage, it.toURI().toURL())
            jCodeModel.build(outputDir.get().asFile)
        }
    }

    private fun createRuleFactory(config: GenerationConfig): RuleFactory {
        val ruleFactoryClass: Class<out RuleFactory> = config.customRuleFactory

        if (!RuleFactory::class.java.isAssignableFrom(ruleFactoryClass)) {
            throw InvalidUserDataException("Custom rule factory class is not a valid rule factory class")
        }

        val ruleFactory = ruleFactoryClass.getDeclaredConstructor().newInstance()
        ruleFactory.annotator =  getAnnotator(config)
        ruleFactory.generationConfig = config
        ruleFactory.schemaStore = SchemaStore(createContentResolver(config))

        return ruleFactory
    }

    private fun getAnnotator(config: GenerationConfig): Annotator {
        val annotatorFactory = AnnotatorFactory(config)
        return annotatorFactory.getAnnotator(
            annotatorFactory.getAnnotator(config.annotationStyle),
            annotatorFactory.getAnnotator(config.customAnnotator)
        )
    }

    private fun createContentResolver(config: GenerationConfig): ContentResolver {
        return when(config.sourceType) {
            SourceType.YAML, SourceType.JSONSCHEMA -> ContentResolver(YAMLFactory())
            else -> ContentResolver()
        }
    }

    private fun createSchemaGenerator(config: GenerationConfig): SchemaGenerator {
        return when(config.sourceType) {
            SourceType.YAML, SourceType.YAMLSCHEMA -> SchemaGenerator(YAMLFactory())
            else -> SchemaGenerator()
        }
    }

}