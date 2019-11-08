plugins {
    java
}

repositories { mavenCentral() }

dependencies {
	implementation("it.unibo.alchemist:alchemist:+")
}

val alchemistGroup = "Run Alchemist"
val runAll by tasks.register<DefaultTask>("runAll") {
    group = alchemistGroup
    description = "Launches all simulations one by one"
}
File(rootProject.rootDir.path + "/src/main/yaml").listFiles()
    .filter { it.name.matches(Regex("""\d{2}-.*\.yml""")) }
    .sortedBy { it.nameWithoutExtension }
    .forEach {
        val task by tasks.register<JavaExec>(it.nameWithoutExtension) {
            group = alchemistGroup
            description = "Launches simulation ${it.nameWithoutExtension}"
            main = "it.unibo.alchemist.Alchemist"
            classpath = sourceSets["main"].runtimeClasspath
            args(
                "-y", it.absolutePath,
                "-g", "effects/${it.nameWithoutExtension}.aes"
            )
        }
        runAll.dependsOn(task)
    }

// task "runAlchemist"(type: JavaExec) {
// 	if (project.hasProperty("simulation")) {
// 		classpath = sourceSets.main.runtimeClasspath
// 		main = 'it.unibo.alchemist.Alchemist'
// 		args("-y", "src/main/yaml/${simulation}.yml", "-g", "effects/${simulation}.aes")
// 	} else {
// 		println "Simulation unspecified. Use\ngradle runAlchemist -Psimulation=SIMULATIONFILE"
// 	}
// }
// runAlchemist.dependsOn(compileJava)
// 
// defaultTasks('runAlchemist')
