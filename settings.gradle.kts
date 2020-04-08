import de.fayard.dependencies.bootstrapRefreshVersionsAndDependencies

rootProject.name = "alchemist-sapere-incarnation-tutorial"

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard:dependencies:+")
}

bootstrapRefreshVersionsAndDependencies()
