# **Introduction to json2java-gradle-plugin**

Json2Java Gradle Plugin - Generates java sources files from json and yaml schema files.

This Gradle plugin provides the capability to java source files from json and yaml schema files.

Compatibility Notes
-------------------

The plugin is build on gradle version 6.7

The Java version used to compile the plugin 1.8

How To Use
----------

Gradle Groovy DSL

```groovy
plugins {
      id "com.rameshkp.json2java-gradle-plugin" version "1.0.0"
}
```

Gradle Kotlin DSL
```kotlin
plugins {
    id("com.rameshkp.json2java-gradle-plugin") version "1.0.0"
}
```

Note: For latest versions of the plugins please check the gradle plugin portal.

The plugin works well with gradle kotlin dsl language.

What does this plugin do
-----------------------

Let's consider a sample schema Animal.jsonschema provided as input to the plugin

```json
{
    "$schema": "http://json-schema.org/draft-04/schema#",
    "properties": {
        "id": {
            "type": "integer"
        },
        "name": {
            "type": "string"
        },
        "description": {
            "type": "string"
        }
    },
    "additionalProperties": true,
    "type": "object"
}
```

The plugin converts this sample schema to a Java class called Animal.java

```java

import java.util.HashMap;
import java.util.Map;

public class Animal {

    private Integer id;
    private String name;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
```

How the plugin works
------------

When the user adds this plugin to their build file, the plugin creates a task and an extension

1. json2Java - Task

2. json2Java - Extension

Running the task json2Java will take all the json files and convert it java files.

```bash
gradle json2java
``` 

Using json2java extension users can perform customization as explained below in the customization section


How to provide inputs
---------------------

By default, the json2java plugin is configured to look for schema files in the following directory.

`src/json2java/schema`

However, user can override the location or provide additional location by using either one of the following methods.

#### Method 1: Using SourceSet DSL

```kotlin
sourceSets {
    json2java {
        schema {
            srcDir("/home/user/somelocation") // Providing absolute path
            srcDir("json-src") // this is relative to project directory
        }
    }
}
```

#### Method 2: Using json2Java extension

```kotlin
json2Java {
    input.from("/home/user/somelocation", "json-src")
}
```

How to configure outputs
-----------------------
By default, user doesn't have to configure the output. The plugin automatically configures the output and places
the generated java source files in the following directory

`build/generated-sources/json2java`

However, if the user wishes to change this location they can do so by setting the property outputDir in json2Java extension
as shown below

```kotlin
json2Java {
    outputDir.set(file("$buildDir/some-other-location"))
}
```

Plugin behaviour
---------------
#### 1. Java Plugin
when java plugin is added to the build file along with this plugin, following additional configurations are done automatically

1. `json2java` plugin output is automatically added as a source to java plugin


2. `compileJava` tasks is automatically made to depend on `json2Java` task. This means when user calls `compileJava` task, 
   `json2java` task is called first to generate java source files from schemas and then java compile is called to compile the generated source files. 

Minimum configuration required
-----------------------------
The following shows the build.gradle.kts file required to use this plugin with minimum configuration. 

It is assumed that all the json schema file is located in the `src/json2java/schema` directory, and some additional schema are located in `jsonschema` folder in the project build directory

```kotlin
plugins {
   id("com.rameshkp.json2java-gradle-plugin") version "0.0.1-SNAPSHOT"

   sourceSets {
      json2java {
         schema {
            srcDir("$buildDir/jsonschema")
         }
      }
   }
}
```

Customization
-------------

The following additional customizations can be done using the json2Java extension

```kotlin
json2Java {
   input.from("src/java2json/schema")
   outputDir.set(file("$buildDir/generated-sources/json2java"))
   generateBuilders.set(false)
   includeTypeInfo.set(false)
   usePrimitives.set(false)
   targetPackage.set("")
   propertyWordDelimiters.set(charArrayOf('-', ' ', '_'))
   useLongIntegers.set(false)
   useDoubleNumbers.set(true)
   includeHashcodeAndEquals.set(true)
   includeToString.set(true)
   toStringExcludes.set(arrayOf(""))
   useTitleAsClassname.set(false)
   annotationStyle.set(org.jsonschema2pojo.AnnotationStyle.JACKSON)
   inclusionLevel.set(org.jsonschema2pojo.InclusionLevel.NON_NULL)
   includeJsr303Annotations.set(false)
   includeJsr305Annotations.set(false)
   useOptionalForGetters.set(false)
   sourceType.set(org.jsonschema2pojo.SourceType.JSONSCHEMA)
   outputEncoding.set("UTF-8")
   useJodaDates.set(false)
   useJodaLocalDates.set(false)
   useJodaLocalTimes.set(false)
   parcelable.set(false)
   serializable.set(false)
   initializeCollections.set(true)
   classNamePrefix.set("")
   classNameSuffix.set("")
   fileExtensions.set(arrayOf())
   useBigIntegers.set(false)
   useBigDecimals.set(false)
   includeConstructors.set(false)
   constructorsRequiredPropertiesOnly.set(false)
   includeRequiredPropertiesConstructor.set(false)
   includeAllPropertiesConstructor.set(false)
   includeCopyConstructor.set(false)
   includeAdditionalProperties.set(false)
   includeGetters.set(true)
   includeSetters.set(true)
   targetVersion.set("1.6")
   includeDynamicAccessors.set(false)
   includeDynamicGetters.set(false)
   includeDynamicSetters.set(false)
   includeDynamicBuilders.set(false)
   dateTimeType.set("")
   dateType.set("")
   timeType.set("")
   formatDateTimes.set(false)
   formatDates.set(false)
   formatTimes.set(false)
   refFragmentPathDelimiters.set("#/.")
   customDatePattern.set("")
   customTimePattern.set("")
   customDateTimePattern.set("")
   formatTypeMapping.set(emptyMap())
   userInnerClassBuilders.set(false)
   includeConstructorPropertiesAnnotation.set(false)
}
```
### Parameters description and their default values
Parameter | Description | Required | Default
--------- | ----------- | -------- | -------
`input` | List of input dirs or files | No | src/json2java/schema
`outputDir` | Output dir to store the generated java file | No | build/generated-sources/json2java
`generateBuilders` | Whether to generate builder-style methods of the form withXxx(value) (that return this) alongside the standard, void-return setters | No | false
`includeTypeInfo` | whether to include json type information. Commonly used to support polymorphic type deserialization. | No | false
`usePrimitives` | Whether to use primitives (long, double, boolean) instead of wrapper types where possible when generating bean properties (has the side-effect of making those properties non-null). | No | false
`targetPackage` | Package name used for generated Java classes (for types where a fully qualified name has not been supplied in the schema using the 'javaType' property). | No | ""
`propertyWordDelimiters` | The characters that should be considered as word delimiters when creating Java Bean property names from JSON property names. If blank or not set, JSON properties will be considered to contain a single word when creating Java Bean property names | No | `charArrayOf('-', ' ', '_')` kotlin dsl
`useLongIntegers` | Whether to use the java type long (or Long) instead of int (or Integer) when representing the JSON Schema type 'integer'. | No | false
`useDoubleNumbers` |  Whether to use the java type double (or Double) instead of float (or Float) when representing the JSON Schema type 'number' | No | true
`includeHashcodeAndEquals` | Whether to include hashCode and equals methods in generated Java types. | No | true
`includeToString` | Whether to include a toString method in generated Java types. | No | true
`toStringExcludes` | An array of strings representing fields that should be excluded from toString methods | No | `arrayOf()` kotlin dsl
`useTitleAsClassname` |  When true the title is used as class name | No | false
`annotationStyle` | The style of annotations to use in the generated Java types. Supported values: <br/>org.jsonschema2pojo.AnnotationStyle.NONE (apply no annotations at all)<br/>org.jsonschema2pojo.AnnotationStyle.JACKSON (alias of jackson2)<br/>org.jsonschema2pojo.AnnotationStyle.JACKSON1 (apply annotations from the Jackson 1.x library)<br/>org.jsonschema2pojo.AnnotationStyle.JACKSON2 (apply annotations from the Jackson 2.x library)<br/>org.jsonschema2pojo.AnnotationStyle.GSON (apply annotations from the Gson library)<br/>org.jsonschema2pojo.AnnotationStyle.MOSHI1 (apply annotations from the Moshi 1.x library)<br/> | No | org.jsonschema2pojo.AnnotationStyle.JACKSON
`inclusionLevel` | The 'inclusionLevel' option for Jackson1 and Jackson2 serializers. Level of inclusion to set in the generated Java types. Supported Values<br/>org.jsonschema2pojo.InclusionLevel.ALWAYS<br/>org.jsonschema2pojo.InclusionLevel.NON_ABSENT<br/>org.jsonschema2pojo.InclusionLevel.NON_DEFAULT<br/>org.jsonschema2pojo.InclusionLevel.NON_EMPTY<br/>org.jsonschema2pojo.InclusionLevel.NON_NULL<br/>org.jsonschema2pojo.InclusionLevel.USE_DEFAULTS | No | org.jsonschema2pojo.InclusionLevel.NON_NULL
`includeJsr303Annotations` | Whether to include JSR-303/349 annotations (for schema rules like minimum, maximum, etc) in generated Java types. Schema rules and the annotation they produce:<br/>- maximum = @DecimalMax<br/>- minimum = @DecimalMin<br/>- minItems,maxItems = @Size<br/>- minLength,maxLength = @Size<br/>- pattern = @Pattern<br/>- required = @NotNull<br/>Any Java fields which are an object or array of objects will be annotated with @Valid to support validation of an entire document tree. | No | false
`includeJsr305Annotations` | Whether to include JSR-305 annotations | No | false
`useOptionalForGetters` | Whether to use {@link java.util.Optional} as return type for getters of non-required fields | No | false
`sourceType` | The type of input documents that will be read. Supported values:<br/>- org.jsonschema2pojo.SourceType.JSONSCHEMA (schema documents, containing formal rules that describe the structure of JSON data)<br/>- org.jsonschema2pojo.SourceType.JSON (documents that represent an example of the kind of JSON data that the generated Java types will be mapped to)<br/>- org.jsonschema2pojo.SourceType.YAMLSCHEMA (JSON schema documents, represented as YAML)<br/>- org.jsonschema2pojo.SourceType.YAML (documents that represent an example of the kind of YAML (or JSON) data that the generated Java types will be mapped to) | No | org.jsonschema2pojo.SourceType.JSONSCHEMA
`outputEncoding` | The character encoding that should be used when writing the generated Java source files | No | "UTF-8"
`useJodaDates` | Whether to use {@link org.joda.time.DateTime} instead of {@link java.util.Date} when adding date type fields to generated Java types.| No | false
`useJodaLocalDates` | Whether to use {@link org.joda.time.LocalDate} instead of string when adding string type fields with a format of date (not date-time) to generated Java types. | No | false
`useJodaLocalTimes` | Whether to use {@link org.joda.time.LocalTime} instead of string when adding string type fields with a format of time (not date-time) to generated Java types | No | false
`parcelable` |  Whether to make the generated types 'parcelable' (for Android development) | No | false
`serializable` | Whether to make the generated types 'serializable' | No | false
`initializeCollections` | Whether to initialize Set and List fields as empty collections, or leave them as null. | No | true
`classNamePrefix` | Whether to add a prefix to generated classes. | No | ""
`classNameSuffix` | Whether to add a suffix to generated classes. | No | ""
`fileExtensions` | An array of strings that should be considered as file extensions and therefore not included in class names. | No | `arrayOf()` kotlin dsl
`useBigIntegers` | Whether to use the java type BigInteger when representing the JSON Schema type 'integer'. Note that this configuration overrides useLongIntegers | No | false
`useBigDecimals` | Whether to use the java type BigDecimal when representing the JSON Schema type 'number'. Note that this configuration overrides useDoubleNumbers | No | false
`includeConstructors` | Whether to generate constructors or not. | No | false
`constructorsRequiredPropertiesOnly` | This is a legacy configuration option used to turn on the `includeAllPropertiesConstructor` and off the `includeAllPropertiesConstructor` configuration options. It is specifically tied to the `includeConstructors` property, and will do nothing if that property is not enabled | No | false
`includeRequiredPropertiesConstructor` | This property works in collaboration with the `includeConstructors` configuration option and is incompatible with `constructorsRequiredPropertiesOnly`, and will have no effect if `includeConstructors` is not set to true. If `includeConstructors` is set to true then this configuration determines whether the resulting object should include a constructor with only the required properties as parameters. | No | false
`includeAllPropertiesConstructor` | This property works in collaboration with the `includeConstructors` configuration option and is incompatible with `constructorsRequiredPropertiesOnly`, and will have no effect if `includeConstructors` is not set to true. If `includeConstructors` is set to true then this configuration determines whether the resulting object should include a constructor with all listed properties as parameters. | No | false
`includeCopyConstructor` | This property works in collaboration with the `includeConstructors` configuration option and is incompatible with `constructorsRequiredPropertiesOnly` and will have no effect if `includeConstructors` is not set to true. If `includeConstructors` is set to true then this configuration determines whether the resulting object should include a constructor the class itself as a parameter, with the expectation that all properties from the originating class will assigned to the new class. | No | false
`includeAdditionalProperties` | Whether to allow 'additional properties' support in objects. Setting this to false will disable additional properties support, regardless of the input schema(s) | No | true
`includeGetters` | Whether to include getters or to omit these accessor methods and create public fields instead. | No | true
`includeSetters` | Whether to include setters or to omit these accessor methods and create public fields instead. | No | true
`targetVersion` | The target version for generated source files | No | "1.6"
`includeDynamicAccessors` | Whether to include dynamic getters, setters, and builders or to omit these methods | No | false
`includeDynamicGetters` | Whether to include dynamic getters or to omit these methods | No | false
`includeDynamicSetters` | Whether to include dynamic setters or to omit these methods | No | false
`includeDynamicBuilders` | Whether to include dynamic builders or to omit these methods | No | false
`dateTimeType` | The java type to use instead of {@link java.util.Date} when adding date type fields to generate Java types.<br/>Example values<br/>org.joda.time.LocalTime (Joda)<br/>java.time.LocalTime (JSR310)<br/>null (default behaviour) | No | null
`dateType` | The java type to use instead of string when adding string type fields with a format of date (not date-time) to generated Java types<br/>Example values<br/>org.joda.time.LocalTime (Joda)<br/>java.time.LocalTime (JSR310)<br/>null (default behaviour) | No | null
`timeType` | The java type to use instead of string when adding string type fields with a format of time (not date-time) to generated Java types<br/>Example values<br/>org.joda.time.LocalTime (Joda)<br/>java.time.LocalTime (JSR310)<br/>null (default behaviour) | No | null
`formatDateTimes` | Whether the fields of type <code>date-type</code> have the <code>@JsonFormat</code> annotation with pattern set to the default value of <code>yyyy-MM-dd'T'HH:mm:ss.SSSZ</code> | No | false
`formatDates` | Whether the fields of type <code>date</code> have the <code>@JsonFormat</code> annotation with pattern set to the default value of <code>yyyy-MM-dd</code> | No | false
`formatTimes` |  Whether the fields of type <code>time</code> have the <code>@JsonFormat</code> annotation with pattern set to the default value of <code>HH:mm:ss.SSS</code> | No | false
`refFragmentPathDelimiters` | A string containing any characters that should act as path delimiters when resolving $ref fragments. By default, #, / and . are used in an attempt to support JSON Pointer and JSON Path | No | "#/."
`customDatePattern` | The custom format that dates will use when types are serialized. Requires support from your JSON binding library. | No | null
`customTimePattern` | The custom format that time will use when types are serialized. Requires support from your JSON binding library. | No | null
`customDateTimePattern` | The custom format that date time will use when types are serialized. Requires support from your JSON binding library. | No | null
`formatTypeMapping` | An optional mapping from format identifier (e.g. 'uri') to fully qualified type name (e.g. 'java.net.URI'). | No | `emptyMap()` kotlin dsl 
`userInnerClassBuilders` | If set to true, then the gang of four builder pattern will be used to generate builders on generated classes. Note: This property works in collaboration with the generateBuilders property. If the generateBuilders is false, then this property will not do anything. | No | false
`includeConstructorPropertiesAnnotation` |  whether to include JDK Constructor Properties. Used by serialization libraries to get parameter names of constructors at runtime. (Not Available on Android) | No | false

# Building the plugin

TODO