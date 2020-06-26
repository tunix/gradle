dependencies {
    api(kotlin("stdlib"))
    implementation(project(":cleanup"))
    implementation(project(":basics"))
    implementation(project(":packaging"))
    implementation(project(":moduleIdentity"))
    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    plugins {
        register("crossVersionTests") {
            id = "gradlebuild.cross-version-tests"
            implementationClass = "org.gradle.gradlebuild.test.integrationtests.CrossVersionTestsPlugin"
        }
        register("distributionTesting") {
            id = "gradlebuild.distribution-testing"
            implementationClass = "org.gradle.gradlebuild.test.integrationtests.DistributionTestingPlugin"
        }
        register("integrationTests") {
            id = "gradlebuild.integration-tests"
            implementationClass = "org.gradle.gradlebuild.test.integrationtests.IntegrationTestsPlugin"
        }
    }
}
