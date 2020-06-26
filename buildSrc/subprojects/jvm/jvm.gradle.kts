dependencies {
    implementation(project(":basics"))
    implementation(project(":moduleIdentity"))
    implementation(project(":packaging")) // TODO move ClasspathManifest

    implementation("org.eclipse.jgit:org.eclipse.jgit:5.7.0.202003110725-r")
    implementation("org.jsoup:jsoup:1.11.3")
    implementation("com.google.guava:guava")
    implementation("org.ow2.asm:asm:7.1")
    implementation("org.ow2.asm:asm-commons:7.1")
    implementation("com.google.code.gson:gson")
    implementation("org.gradle:test-retry-gradle-plugin:1.1.6")

    implementation("com.thoughtworks.qdox:qdox:2.0-M9") {
        because("ParameterNamesIndex")
    }
}
