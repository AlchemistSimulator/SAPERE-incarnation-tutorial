plugins {
    java
}

repositories { mavenCentral() }

dependencies {
	implementation("it.unibo.alchemist:alchemist:+")
}

File(rootProject.rootDir.path + "/src/main/yaml").listFiles()
    .filter { it.name.matches(Regex("""\d{2}-.*\.yml""")) }
    .forEach {
        tasks.register<JavaExec>(it.nameWithoutExtension) {
            group = "Run Alchemist"
            description = "Launches simulation ${it.nameWithoutExtension}"
            main = "it.unibo.alchemist.Alchemist"
            classpath = sourceSets["main"].runtimeClasspath
            args(
                "-y", it.absolutePath,
                "-g", "effects/${it.nameWithoutExtension}.aes"
            )
        }
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
