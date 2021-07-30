plugins { id(PluginId.library) }
useKotlin()
setupCore()
android { buildFeatures.dataBinding = true }

dependencies{
    implementation("com.github.wzmyyj.FeDiff:lib_diff_api:1.2.0-beta1")
    kapt2("com.github.wzmyyj.FeDiff:lib_diff_compiler:1.2.0-beta1")
}