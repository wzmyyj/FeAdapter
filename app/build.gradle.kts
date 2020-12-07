plugins { id(PluginId.application) }
useKotlin()
setupCore()
android {
    buildFeatures.dataBinding = true
    defaultConfig.multiDexEnabled = true
    defaultConfig.applicationId = AppConfig.applicationId
    flavorDimensions("channel")

    defaultConfig{
        javaCompileOptions{
            annotationProcessorOptions {
                argument("moduleNameForDiffAPT","app")
                argument("packageNameForDiffAPT",AppConfig.applicationId)
            }
        }
    }
}
dependencies {
    implementation(Dependencies.androidx_coreKtx)
    implementation(Dependencies.androidx_constraintlayout)
    implementation(Dependencies.androidx_recyclerview)
    implementation(Dependencies.androidx_lifecycle_ext)
    implementation(Dependencies.androidx_lifecycle_java8)

//    api(project(":lib_adapter"))

    implementation(project(":lib_diff_annotation"))
    implementation(project(":lib_diff_api"))
    kapt2(project(":lib_diff_compiler"))
}
