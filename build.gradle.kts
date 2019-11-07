plugins {
    java
}

repositories { mavenCentral() }

dependencies {
	implementation("it.unibo.alchemist:alchemist:+")
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
