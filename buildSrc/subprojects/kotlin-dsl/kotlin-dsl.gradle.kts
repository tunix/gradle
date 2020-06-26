
dependencies {
    implementation(project(":basics"))

    // TODO check which are needed!
    api(kotlin("gradle-plugin"))
    api(kotlin("stdlib-jdk8"))
    api(kotlin("reflect"))
    api(kotlin("compiler-embeddable"))

    implementation("org.gradle.kotlin:gradle-kotlin-dsl-conventions:0.5.0")

    implementation("org.ow2.asm:asm:7.1")

    testImplementation("junit:junit:4.13")
    testImplementation("com.nhaarman:mockito-kotlin:1.6.0")
}
