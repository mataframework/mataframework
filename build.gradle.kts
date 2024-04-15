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

dependencies {
    // https://mvnrepository.com/artifact/org.jetbrains/annotations
    implementation("org.jetbrains", "annotations", "24.0.1")
    // https://mvnrepository.com/artifact/io.appium/java-client
    implementation("io.appium", "java-client", "7.6.0")
    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io", "commons-io", "2.14.0")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-text
    implementation("org.apache.commons", "commons-text", "1.10.0")

    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.10.0")
}

tasks.test {
    val platform = if (project.hasProperty("platform")) project.property("platform") as String else ""
    useJUnitPlatform() {
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