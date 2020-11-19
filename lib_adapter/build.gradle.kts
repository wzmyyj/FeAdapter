plugins { id(Android.library) }
useKotlin()
setupCore()
android { buildFeatures.dataBinding = true }
dependencies {
    implementation(Dependencies.androidx_recyclerview)
}

useMaven()

apply {
    plugin("com.github.panpf.bintray-publish")
}

configure<com.github.panpf.bintray.publish.PublishExtension> {
    userOrg = "wzmyyj"
    groupId = "top.wzmyyj.adapter"
    artifactId = "adapter"
    publishVersion = "1.0.0"
    desc = "DataBinding RecyclerView Adapter."
    website = "https://github.com/wzmyyj/FeAdapter"
}
