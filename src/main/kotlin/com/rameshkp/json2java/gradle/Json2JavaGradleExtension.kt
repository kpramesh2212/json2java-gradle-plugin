@file:Suppress("unused")

package com.rameshkp.json2java.gradle

import org.gradle.api.file.*
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.jsonschema2pojo.*
import org.jsonschema2pojo.rules.RuleFactory
import java.io.FileFilter
import javax.inject.Inject

@Suppress("UnstableApiUsage")
open class Json2JavaGradleExtension @Inject constructor(objectFactory: ObjectFactory) {
    val generateBuilders: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeTypeInfo: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val usePrimitives: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val targetPackage: Property<String> = objectFactory.property(String::class.java)
    val propertyWordDelimiters: Property<CharArray> = objectFactory.property(CharArray::class.java)
    val useLongIntegers: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val useDoubleNumbers: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val includeHashcodeAndEquals: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeToString: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val toStringExcludes: Property<Array<String>> = objectFactory.property(Array<String>::class.java)

    val useTitleAsClassname: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val annotationStyle: Property<AnnotationStyle> = objectFactory.property(AnnotationStyle::class.java)
    val inclusionLevel: Property<InclusionLevel> = objectFactory.property(InclusionLevel::class.java)
    val customAnnotator: Property<out Annotator> = objectFactory.property(Annotator::class.java)
    val customRuleFactory: Property<out RuleFactory> = objectFactory.property(RuleFactory::class.java)

    val includeJsr303Annotations: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeJsr305Annotations: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val useOptionalForGetters: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val sourceType: Property<SourceType> = objectFactory.property(SourceType::class.java)
    val outputEncoding: Property<String> = objectFactory.property(String::class.java)
    val removeOldOutput: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val useJodaDates: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val useJodaLocalDates: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val useJodaLocalTimes: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val parcelable: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val serializable: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val fileFilter: Property<FileFilter> = objectFactory.property(FileFilter::class.java)
    val initializeCollections: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val classNamePrefix: Property<String> = objectFactory.property(String::class.java)
    val classNameSuffix: Property<String> = objectFactory.property(String::class.java)
    val fileExtensions: Property<Array<String>> = objectFactory.property(Array<String>::class.java)

    val useBigIntegers: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val useBigDecimals: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeConstructors: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val constructorsRequiredPropertiesOnly: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeRequiredPropertiesConstructor: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeAllPropertiesConstructor: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeCopyConstructor: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeAdditionalProperties: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeGetters: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeSetters: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val targetVersion: Property<String> = objectFactory.property(String::class.java)
    val includeDynamicAccessors: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeDynamicGetters: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeDynamicSetters: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeDynamicBuilders: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val dateTimeType: Property<String> = objectFactory.property(String::class.java)
    val dateType: Property<String> = objectFactory.property(String::class.java)
    val timeType: Property<String> = objectFactory.property(String::class.java)
    val formatDateTimes: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val formatDates: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val formatTimes: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val refFragmentPathDelimiters: Property<String> = objectFactory.property(String::class.java)
    val customDatePattern: Property<String> = objectFactory.property(String::class.java)
    val customTimePattern: Property<String> = objectFactory.property(String::class.java)
    val customDateTimePattern: Property<String> = objectFactory.property(String::class.java)

    val sourceSortOrder: Property<SourceSortOrder> = objectFactory.property(SourceSortOrder::class.java)
    val targetLanguage: Property<Language> = objectFactory.property(Language::class.java)
    val formatTypeMapping: MapProperty<String,String> = objectFactory.mapProperty(String::class.java, String::class.java)

    val userInnerClassBuilders: Property<Boolean> = objectFactory.property(Boolean::class.java)
    val includeConstructorPropertiesAnnotation: Property<Boolean> = objectFactory.property(Boolean::class.java)

    val outputDir: DirectoryProperty = objectFactory.directoryProperty()
    val input = objectFactory.fileCollection()
}
