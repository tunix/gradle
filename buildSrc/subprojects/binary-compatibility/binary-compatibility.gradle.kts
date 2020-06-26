plugins {
    `groovy-gradle-plugin`
}

dependencies {
    api("me.champeau.gradle:japicmp-gradle-plugin:0.2.9")

    implementation(project(":basics"))
    implementation(project(":moduleIdentity"))

    implementation("com.google.code.gson:gson")
    implementation("com.google.guava:guava")
    implementation("org.javassist:javassist:3.23.0-GA")
    implementation("com.github.javaparser:javaparser-core")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.1.0")

    implementation(kotlin("compiler-embeddable"))

    testImplementation("org.jsoup:jsoup:1.11.3")
}

tasks.compileGroovy.configure {
    classpath += files(tasks.compileKotlin)
}
