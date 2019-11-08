plugins {
    java
}

repositories { mavenCentral() }

dependencies {
	implementation("it.unibo.alchemist:alchemist:+")
}

val alchemistGroup = "Run Alchemist"
/*
 * This task creates a Jar file containing a description of the classpath, in order to prevent the java command
 * to be too long for the host OS to be executed.
 */
val classpathJar by tasks.register<Jar>("classpathJar") {
    appendix = "classpath"
    doFirst {
        manifest {
            val classpath = sourceSets["main"].runtimeClasspath.files
                .filter { it.isFile && it.extension == "jar" }
                .joinToString (separator = " ") { it.absolutePath }
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
/*
 * Scan the folder with the simulation files, and create a task for each one of them.
 */
File(rootProject.rootDir.path + "/src/main/yaml").listFiles()
    .filter { it.name.matches(Regex("""\d{2}-.*\.yml""")) }
    .sortedBy { it.nameWithoutExtension }
    .forEach {
        val task by tasks.register<JavaExec>(it.nameWithoutExtension) {
            group = alchemistGroup
            description = "Launches simulation ${it.nameWithoutExtension}"
            main = "it.unibo.alchemist.Alchemist"
            classpath = sourceSets["main"].runtimeClasspath.filter { it.isDirectory } + classpathJar.outputs.files
            args(
                "-y", it.absolutePath,
                "-g", "effects/${it.nameWithoutExtension}.aes"
            )
            if (System.getenv("CI") == "true") {
                args("-hl", "-t", "10")
            }
        }
        task.dependsOn(classpathJar)
        runAll.dependsOn(task)
    }
