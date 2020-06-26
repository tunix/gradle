package gradlebuild

import gradlebuild.basics.extension.BuildJvms

plugins {
    `java-base`
}

val testJavaHomePropertyName = "testJavaHome"

val testJavaHomePath = providers.gradleProperty(testJavaHomePropertyName).forUseAtConfigurationTime()
    .orElse(providers.systemProperty(testJavaHomePropertyName).forUseAtConfigurationTime())
    .orElse(providers.environmentVariable(testJavaHomePropertyName).forUseAtConfigurationTime())
val testJavaHome = rootProject.layout.projectDirectory.dir(testJavaHomePath)

extensions.create<BuildJvms>("buildJvms", javaInstalls, testJavaHome)
