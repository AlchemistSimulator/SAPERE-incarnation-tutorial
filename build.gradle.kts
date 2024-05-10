import java.awt.GraphicsEnvironment

plugins {
    java
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(
            file("Dockerfile")
                .readLines()
                .first { it.contains("FROM") }
                .substringAfter("eclipse-temurin:")
                .toInt()
        )
    }
}

repositories { mavenCentral() }

dependencies {
    implementation(libs.bundles.alchemist.sapere.tutorial)
}

val alchemistGroup = "Run Alchemist"
/*
 * This task creates a Jar file containing a description of the classpath, in order to prevent the java command
 * to be too long for the host OS to be executed.
 */
val classpathJar by tasks.register<Jar>("classpathJar") {
    group = alchemistGroup
    description = "Creates a jar file with a manifest pointing to all the jar resources needed for the runtime"
    archiveClassifier.set("classpath")
    doFirst {
        manifest {
            val classpath = sourceSets["main"].runtimeClasspath.files
                .filter { it.isFile && it.extension == "jar" }
                .joinToString(separator = " ", prefix = " ") { it.absolutePath }
            attributes("Class-Path" to classpath)
        }
    }
}
/*
 * This task is used to run all experiments in sequence
 */
val runAll by tasks.register<DefaultTask>("runAll") {
    group = alchemistGroup
    description = "Launches all simulations"
}
val isInCI = System.getenv("CI") == "true"
/*
 * Scan the folder with the simulation files, and create a task for each one of them.
 */
File(rootProject.rootDir.path + "/src/main/yaml").listFiles()
    ?.filter { it.name.matches(Regex("""\d{2}-.*\.yml""")) }
    ?.sortedBy { it.nameWithoutExtension }
    ?.forEach {
        val task by tasks.register<JavaExec>(it.nameWithoutExtension) {
            group = alchemistGroup
            description = "Launches simulation ${it.nameWithoutExtension}"
            mainClass.set("it.unibo.alchemist.Alchemist")
            classpath = sourceSets["main"].runtimeClasspath
            args("run", it.absolutePath)
            // checks if the environment is headless
            if (!(GraphicsEnvironment.isHeadless() || isInCI)) {
                args(
                    "--override",
                    """
                    monitors: 
                        type: SwingGUI
                        parameters: 
                            graphics: effects/${it.nameWithoutExtension}.json
                            failOnHeadless: true
                    launcher: 
                        parameters:
                            auto-start: $isInCI
                    """.trimIndent()
                )
            }
            if (isInCI) {
                args("--override", "terminate: { type: AfterTime, parameters: 10 }")
            }
        }
        // task.dependsOn(classpathJar) // Uncomment to switch to jar-based cp resolution
        runAll.dependsOn(task)
    }
    ?: error("Cannot list simulation files")
