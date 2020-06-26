package gradlebuild

import java.nio.charset.Charset

plugins {
    id("gradlebuild.available-java-installations")
}

tasks.register("verifyIsProductionBuildEnvironment") {
    val buildJvms = project.buildJvms
    doLast {
        buildJvms.validateForProductionEnvironment()
        val systemCharset = Charset.defaultCharset().name()
        assert(systemCharset == "UTF-8") {
            "Platform encoding must be UTF-8. Is currently $systemCharset. Set -Dfile.encoding=UTF-8"
        }
    }
}
