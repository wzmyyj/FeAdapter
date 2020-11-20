plugins { id(PluginId.java_library) }

dependencies {
    fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation(Dependencies.auto_service)
    implementation(Dependencies.java_poet)
    implementation(project(":lib_diff_annotation"))
}

// encoding utf-8.
tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}