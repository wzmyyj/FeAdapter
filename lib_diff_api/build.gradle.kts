plugins { id(PluginId.library) }
useKotlin()
setupCore()
dependencies {
    implementation(Dependencies.androidx_recyclerview)
}

//
//apply { plugin(PluginId.bintray_publish) }
//
//configure<com.github.panpf.bintray.publish.PublishExtension> {
//    userOrg = "wzmyyj"
//    groupId = "top.wzmyyj.adapter"
//    artifactId = "diff"
//    publishVersion = "1.0.0"
//    desc = "DataBinding RecyclerView Adapter."
//    website = "https://github.com/wzmyyj/FeAdapter"
//}
