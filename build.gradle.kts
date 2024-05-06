import java.text.SimpleDateFormat
import java.util.*

plugins {
    kotlin("jvm") version "1.9.0"
}

group = project.property("maven_group") as String
version = project.property("framework_version") as String

repositories {
    mavenCentral()
}

val versions = mapOf(
    "jetbrains-annotations" to "24.0.1",
    "appium" to "7.6.0",
    "junit" to "5.10.0",
    "allure" to "2.25.0",
    "aspectJ" to "1.9.21",
    "commons-io" to "2.14.0",
    "commons-text" to "1.10.0"
)

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}


dependencies {
    agent("org.aspectj:aspectjweaver:${versions["aspectJ"]}")

    implementation(kotlin("reflect"))
    // https://mvnrepository.com/artifact/org.jetbrains/annotations
    implementation("org.jetbrains", "annotations", versions["jetbrains-annotations"])
    // https://mvnrepository.com/artifact/io.appium/java-client
    implementation("io.appium", "java-client", versions["appium"])
    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io", "commons-io", versions["commons-io"])
    // https://mvnrepository.com/artifact/org.apache.commons/commons-text
    implementation("org.apache.commons", "commons-text", versions["commons-text"])
    implementation("org.junit.jupiter", "junit-jupiter-api", versions["junit"])
    // https://mvnrepository.com/artifact/io.qameta.allure/allure-java-commons
    implementation("io.qameta.allure", "allure-java-commons", versions["allure"])


    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter", "junit-jupiter-api", versions["junit"])

    // Import allure-bom to ensure correct versions of all the dependencies are used
    testImplementation(platform("io.qameta.allure:allure-bom:${versions["allure"]}"))
    // Add necessary Allure dependencies to dependencies section
    testImplementation("io.qameta.allure:allure-junit5")
}

tasks.test {
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
    val platform = if (project.hasProperty("platform")) project.property("platform") as String else ""
    useJUnitPlatform {
        if (project.hasProperty("tag") && project.property("tag") != "") {
            val tags = project.property("tag").toString().split(",")
            includeTags(*tags.toTypedArray())
        }
        if (platform == "ios") {
            excludeTags = mutableSetOf("androidOnly")
        } else if (platform == "android") {
            excludeTags = mutableSetOf("iosOnly")
        }
    }
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.property("archives_base_name")}" }
    }
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to project.property("framework_name"),
                "Specification-Vendor" to project.property("framework_author"),
                "Specification-Version" to project.property("framework_version"),
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.property("framework_version"),
                "Implementation-Vendor" to project.property("framework_author"),
                "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date()),
                "Timestamp" to System.currentTimeMillis(),
                "Built-On-Java" to "${System.getProperty("java.vm.version")} (${System.getProperty("java.vm.vendor")})"
            )
        )
    }
}

kotlin {
    jvmToolchain(8)
}