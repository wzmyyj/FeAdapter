plugins { id(Android.library) }
useKotlin()
setupCore()
android{
    @Suppress("DEPRECATION")
    dataBinding.isEnabled = true
}
dependencies {
    implementation(Dependencies.androidx_recyclerview)
}