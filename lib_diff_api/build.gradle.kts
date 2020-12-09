plugins { id(PluginId.library) }
setupCore()
dependencies {
    implementation(project(":lib_diff_annotation"))
}

//
//apply { plugin(PluginId.bintray_publish) }
//
//configure<com.github.panpf.bintray.publish.PublishExtension> {
//    userOrg = "wzmyyj"
//    groupId = "top.wzmyyj.diff"
//    artifactId = "diff_api"
//    publishVersion = "1.0.0"
//    desc = "DataBinding RecyclerView Adapter."
//    website = "https://github.com/wzmyyj/FeAdapter"
//}
