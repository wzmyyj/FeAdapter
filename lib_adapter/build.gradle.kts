plugins { id(PluginId.library) }
useKotlin()
setupCore()
android { buildFeatures.dataBinding = true }
dependencies {
    implementation(Dependencies.androidx_recyclerview)
}

apply { plugin(PluginId.bintray_publish) }

configure<com.github.panpf.bintray.publish.PublishExtension> {
    userOrg = "wzmyyj"
    groupId = "top.wzmyyj.adapter"
    artifactId = "adapter"
    publishVersion = "1.0.1"
    desc = "DataBinding RecyclerView Adapter."
    website = "https://github.com/wzmyyj/FeAdapter"
}
