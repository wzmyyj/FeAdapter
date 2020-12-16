plugins { id(PluginId.application) }
useKotlin()
setupCore()
android {
    buildFeatures.dataBinding = true
    defaultConfig.multiDexEnabled = true
    defaultConfig.applicationId = AppConfig.applicationId
    flavorDimensions("channel")

}
dependencies {
    implementation(Dependencies.androidx_coreKtx)
    implementation(Dependencies.androidx_constraintlayout)
    implementation(Dependencies.androidx_recyclerview)
    implementation(Dependencies.androidx_lifecycle_ext)
    implementation(Dependencies.androidx_lifecycle_java8)

    implementation(project(":lib_adapter"))

    implementation("com.github.wzmyyj.FeDiff:lib_diff_api:1.1.0-beta04")
    kapt2("com.github.wzmyyj.FeDiff:lib_diff_compiler:1.1.0-beta04")
}
