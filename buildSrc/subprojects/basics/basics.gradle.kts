dependencies {
    implementation("com.google.code.gson:gson")

    implementation(kotlin("compiler-embeddable") as String) {
        because("Required by KotlinSourceParser")
    }
    implementation(kotlin("gradle-plugin") as String) {
        because("KotlinSourceSet") // TODO remove?
    }
}
