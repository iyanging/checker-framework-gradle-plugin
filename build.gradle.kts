plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("com.diffplug.spotless") version "7.0.0.BETA3"
}

repositories { mavenCentral() }

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

gradlePlugin {
    val checkerFramework by
        plugins.creating {
            id = "io.github.iyanging.checker-framework"
            implementationClass = "io.github.iyanging.CheckerFrameworkPlugin"
        }
}

// Add a source set for the functional test suite
val functionalTestSourceSet = sourceSets.create("functionalTest") {}

configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])

configurations["functionalTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

// Add a task to run the functional tests
val functionalTest by
    tasks.registering(Test::class) {
        testClassesDirs = functionalTestSourceSet.output.classesDirs
        classpath = functionalTestSourceSet.runtimeClasspath
        useJUnitPlatform()
    }

gradlePlugin.testSourceSets.add(functionalTestSourceSet)

tasks.named<Task>("check") {
    // Run the functional tests as part of `check`
    dependsOn(functionalTest)
}

tasks.named<Test>("test") {
    // Use JUnit Jupiter for unit tests.
    useJUnitPlatform()
}

spotless {
    kotlin {
        ktfmt().kotlinlangStyle().configure {
            it.setMaxWidth(100)
            it.setRemoveUnusedImports(true)
        }
    }
    kotlinGradle {
        ktfmt().kotlinlangStyle().configure {
            it.setMaxWidth(100)
            it.setRemoveUnusedImports(true)
        }
    }
}
