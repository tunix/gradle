dependencies {
    implementation(project(":binaryCompatibility"))
    implementation(project(":buildquality"))
    implementation(project(":cleanup"))
    implementation(project(":basics"))
    implementation(project(":kotlinDsl"))
    implementation(project(":jvm"))
    implementation(project(":packaging"))
    implementation(project(":profiling"))

    implementation("org.gradle.kotlin:gradle-kotlin-dsl-conventions:0.5.0")
}
