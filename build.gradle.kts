// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    apply(from = "repositories.gradle.kts")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(rootProject.extra.get("androidPlugin").toString())
        classpath(kotlin("gradle-plugin", rootProject.extra.get("kotlinVersion").toString()))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            setUrl("${rootProject.projectDir}")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}