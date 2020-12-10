plugins { id(PluginId.java_library) }

dependencies {
    fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar")))
    compileOnly(Dependencies.auto_service)
    annotationProcessor(Dependencies.auto_service)
    implementation(Dependencies.java_poet)
    api(project(":lib_diff_annotation"))
}

// encoding utf-8.
tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
}

tasks.javadoc {
    source = sourceSets.main.get().java.srcDirs()
    classpath += project.files(File.pathSeparator)
    options.encoding = "UTF-8"
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

apply { plugin(PluginId.github_maven) }
group = "com.github.wzmyyj"
version = "1.0.0"


//apply { plugin(PluginId.bintray_publish) }
//
//configure<com.github.panpf.bintray.publish.PublishExtension> {
//    userOrg = "wzmyyj"
//    groupId = "top.wzmyyj.diff"
//    artifactId = "diff_compiler"
//    publishVersion = "1.0.0"
//    desc = "Diff Model Compiler."
//    website = "https://github.com/wzmyyj/FeAdapter"
//}
