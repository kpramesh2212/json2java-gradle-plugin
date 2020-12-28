package com.rameshkp.json2java.gradle

import org.jsonschema2pojo.*
import org.jsonschema2pojo.rules.RuleFactory
import java.io.File
import java.io.FileFilter
import java.net.URL

class Json2JavaGenerationConfig (private val extension: Json2JavaGradleExtension): DefaultGenerationConfig() {

    override fun isGenerateBuilders(): Boolean {
        return extension.generateBuilders.getOrElse(super.isGenerateBuilders())
    }

    override fun isIncludeTypeInfo(): Boolean {
        return extension.includeTypeInfo.getOrElse(super.isIncludeTypeInfo())
    }

    override fun isIncludeConstructorPropertiesAnnotation(): Boolean {
        return extension.includeConstructorPropertiesAnnotation.getOrElse(super.isIncludeConstructorPropertiesAnnotation())
    }

    override fun isUsePrimitives(): Boolean {
        return extension.usePrimitives.getOrElse(super.isUsePrimitives())
    }

    override fun getTargetDirectory(): File {
        return extension.outputDir.get().asFile
    }

    override fun getTargetPackage(): String {
        return extension.targetPackage.getOrElse(super.getTargetPackage())
    }

    override fun getPropertyWordDelimiters(): CharArray {
        return extension.propertyWordDelimiters.getOrElse(super.getPropertyWordDelimiters())
    }

    override fun isUseLongIntegers(): Boolean {
        return extension.useLongIntegers.getOrElse(super.isUseLongIntegers())
    }

    override fun isUseBigIntegers(): Boolean {
        return extension.useBigIntegers.getOrElse(super.isUseBigIntegers())
    }

    override fun isUseDoubleNumbers(): Boolean {
        return extension.useDoubleNumbers.getOrElse(super.isUseDoubleNumbers())
    }

    override fun isUseBigDecimals(): Boolean {
        return extension.useBigDecimals.getOrElse(super.isUseBigDecimals())
    }

    override fun isIncludeHashcodeAndEquals(): Boolean {
        return extension.includeHashcodeAndEquals.getOrElse(super.isIncludeHashcodeAndEquals())
    }

    override fun isIncludeToString(): Boolean {
        return extension.includeToString.getOrElse(super.isIncludeToString())
    }

    override fun getToStringExcludes(): Array<String> {
        return extension.toStringExcludes.getOrElse(super.getToStringExcludes())
    }

    override fun getAnnotationStyle(): AnnotationStyle {
        return extension.annotationStyle.getOrElse(super.getAnnotationStyle())
    }

    override fun isUseTitleAsClassname(): Boolean {
        return extension.useTitleAsClassname.getOrElse(super.isUseTitleAsClassname())
    }

    override fun getInclusionLevel(): InclusionLevel {
        return extension.inclusionLevel.getOrElse(super.getInclusionLevel())
    }

    override fun getCustomAnnotator(): Class<out Annotator> {
        if (extension.customAnnotator.isPresent) {
           return extension.customAnnotator.get().javaClass.asSubclass(Annotator::class.java)
        }
        return super.getCustomAnnotator()
    }

    override fun getCustomRuleFactory(): Class<out RuleFactory> {
        if (extension.customRuleFactory.isPresent) {
            return extension.customRuleFactory.get().javaClass.asSubclass(RuleFactory::class.java)
        }
        return super.getCustomRuleFactory()
    }

    override fun isIncludeJsr303Annotations(): Boolean {
        return extension.includeJsr303Annotations.getOrElse(super.isIncludeJsr303Annotations())
    }

    override fun isIncludeJsr305Annotations(): Boolean {
        return extension.includeJsr305Annotations.getOrElse(super.isIncludeJsr305Annotations())
    }

    override fun isUseOptionalForGetters(): Boolean {
        return extension.useOptionalForGetters.getOrElse(super.isUseOptionalForGetters())
    }

    override fun getSourceType(): SourceType {
        return extension.sourceType.getOrElse(super.getSourceType())
    }

    override fun isRemoveOldOutput(): Boolean {
        return extension.removeOldOutput.getOrElse(super.isRemoveOldOutput())
    }

    override fun getOutputEncoding(): String {
        return extension.outputEncoding.getOrElse(super.getOutputEncoding())
    }

    override fun isUseJodaDates(): Boolean {
        return extension.useJodaDates.getOrElse(super.isUseJodaDates())
    }

    override fun isUseJodaLocalDates(): Boolean {
        return extension.useJodaLocalDates.getOrElse(super.isUseJodaLocalDates())
    }

    override fun isUseJodaLocalTimes(): Boolean {
        return extension.useJodaLocalTimes.getOrElse(super.isUseJodaLocalTimes())
    }

    override fun isParcelable(): Boolean {
        return extension.parcelable.getOrElse(super.isParcelable())
    }

    override fun isSerializable(): Boolean {
        return extension.serializable.getOrElse(super.isSerializable())
    }

    override fun getFileFilter(): FileFilter {
        return extension.fileFilter.getOrElse(super.getFileFilter())
    }

    override fun isInitializeCollections(): Boolean {
        return extension.initializeCollections.getOrElse(super.isInitializeCollections())
    }

    override fun getClassNamePrefix(): String {
        return extension.classNamePrefix.getOrElse(super.getClassNamePrefix())
    }

    override fun getClassNameSuffix(): String {
        return extension.classNameSuffix.getOrElse(super.getClassNameSuffix())
    }

    override fun getFileExtensions(): Array<String> {
        return extension.fileExtensions.getOrElse(super.getFileExtensions())
    }

    override fun isIncludeConstructors(): Boolean {
        return extension.includeConstructors.getOrElse(super.isIncludeConstructors())
    }

    override fun isConstructorsRequiredPropertiesOnly(): Boolean {
        return extension.constructorsRequiredPropertiesOnly.getOrElse(super.isConstructorsRequiredPropertiesOnly())
    }

    override fun isIncludeRequiredPropertiesConstructor(): Boolean {
        return extension.includeRequiredPropertiesConstructor.getOrElse(super.isIncludeRequiredPropertiesConstructor())
    }

    override fun isIncludeAllPropertiesConstructor(): Boolean {
        return extension.includeAllPropertiesConstructor.getOrElse(super.isIncludeAllPropertiesConstructor())
    }

    override fun isIncludeCopyConstructor(): Boolean {
        return extension.includeCopyConstructor.getOrElse(super.isIncludeCopyConstructor())
    }

    override fun isIncludeAdditionalProperties(): Boolean {
        return extension.includeAdditionalProperties.getOrElse(super.isIncludeAdditionalProperties())
    }

    override fun isIncludeGetters(): Boolean {
        return extension.includeGetters.getOrElse(super.isIncludeGetters())
    }

    override fun isIncludeSetters(): Boolean {
        return extension.includeSetters.getOrElse(super.isIncludeSetters())
    }

    override fun getTargetVersion(): String {
        return extension.targetVersion.getOrElse(super.getTargetVersion())
    }

    override fun isIncludeDynamicAccessors(): Boolean {
        return extension.includeDynamicAccessors.getOrElse(super.isIncludeDynamicAccessors())
    }

    override fun isIncludeDynamicGetters(): Boolean {
        return extension.includeDynamicGetters.getOrElse(super.isIncludeDynamicGetters())
    }

    override fun isIncludeDynamicSetters(): Boolean {
        return extension.includeDynamicSetters.getOrElse(super.isIncludeDynamicSetters())
    }

    override fun isIncludeDynamicBuilders(): Boolean {
        return extension.includeDynamicBuilders.getOrElse(super.isIncludeDynamicBuilders())
    }

    override fun getDateTimeType(): String {
        return extension.dateTimeType.getOrElse(super.getDateTimeType())
    }

    override fun getDateType(): String {
        return extension.dateType.getOrElse(super.getDateType())
    }

    override fun getTimeType(): String {
        return extension.dateTimeType.getOrElse(super.getTimeType())
    }

    override fun isFormatDates(): Boolean {
        return extension.formatDates.getOrElse(super.isFormatDates())
    }

    override fun isFormatTimes(): Boolean {
        return extension.formatTimes.getOrElse(super.isFormatTimes())
    }

    override fun isFormatDateTimes(): Boolean {
        return extension.formatDateTimes.getOrElse(super.isFormatDateTimes())
    }

    override fun getCustomDatePattern(): String {
        return extension.customDatePattern.getOrElse(super.getCustomDatePattern())
    }

    override fun getCustomTimePattern(): String {
        return extension.customTimePattern.getOrElse(super.getCustomTimePattern())
    }

    override fun getCustomDateTimePattern(): String {
        return extension.customDateTimePattern.getOrElse(super.getCustomDateTimePattern())
    }

    override fun getRefFragmentPathDelimiters(): String {
        return extension.refFragmentPathDelimiters.getOrElse(super.getRefFragmentPathDelimiters())
    }

    override fun getSourceSortOrder(): SourceSortOrder {
        return extension.sourceSortOrder.getOrElse(super.getSourceSortOrder())
    }

    override fun getTargetLanguage(): Language {
        return extension.targetLanguage.getOrElse(super.getTargetLanguage())
    }

    override fun getFormatTypeMapping(): MutableMap<String, String> {
        return extension.formatTypeMapping.getOrElse(super.getFormatTypeMapping())
    }

    override fun isUseInnerClassBuilders(): Boolean {
        return extension.userInnerClassBuilders.getOrElse(super.isUseInnerClassBuilders())
    }
}