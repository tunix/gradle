/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.tasks.compile.tooling

import org.gradle.integtests.tooling.fixture.ProgressEvents
import org.gradle.integtests.tooling.fixture.TargetGradleVersion
import org.gradle.integtests.tooling.fixture.ToolingApiSpecification
import org.gradle.integtests.tooling.fixture.ToolingApiVersion
import org.gradle.language.fixtures.HelperProcessorFixture
import org.gradle.tooling.events.OperationType
import org.gradle.tooling.events.task.TaskSuccessResult
import org.gradle.tooling.events.task.java.JavaCompileTaskOperationResult

import java.time.Duration

import static org.gradle.tooling.events.task.java.JavaCompileTaskOperationResult.AnnotationProcessorResult.Type.ISOLATING

@ToolingApiVersion('>=5.1')
@TargetGradleVersion('>=4.6')
class JavaCompileTaskOperationResultCrossVersionTest extends ToolingApiSpecification {

    def setup() {
        settingsFile << """
            include 'processor'
        """
        buildFile << """
            allprojects {
                apply plugin: 'java'
            }
            dependencies {
                compileOnly project(':processor')
                annotationProcessor project(':processor')
            }
        """
        file("src/main/java/SomeClass.java") << """
            @Helper class SomeClass {}
        """
        def processorProjectDir = file("processor")
        def fixture = new HelperProcessorFixture()
        fixture.writeApiTo(processorProjectDir)
        fixture.writeAnnotationProcessorTo(processorProjectDir)
        fixture.writeSupportLibraryTo(processorProjectDir)
    }

    @TargetGradleVersion(">=5.1")
    def "reports annotation processor results for JavaCompile task"() {
        when:
        def events = runBuild("compileJava")

        then:
        def operation = events.operation("Task :compileJava")
        operation.task
        operation.result instanceof JavaCompileTaskOperationResult
        with((JavaCompileTaskOperationResult) operation.result) {
            annotationProcessorResults.size() == 1
            with(annotationProcessorResults.first()) {
                className == 'HelperProcessor'
                type == ISOLATING
                duration >= Duration.ZERO
            }
        }

        and:
        def processorOperation = events.operation("Task :processor:compileJava")
        processorOperation.task
        processorOperation.result instanceof JavaCompileTaskOperationResult
        ((JavaCompileTaskOperationResult) processorOperation.result).annotationProcessorResults.empty
    }

    @TargetGradleVersion(">=4.6 <5.1")
    def "reports regular success result for older Gradle versions"() {
        when:
        def events = runBuild("compileJava")

        then:
        def operation = events.operation("Task :compileJava")
        operation.task
        operation.result instanceof TaskSuccessResult
        !(operation.result instanceof JavaCompileTaskOperationResult)
    }

    private ProgressEvents runBuild(task) {
        ProgressEvents events = ProgressEvents.create()
        withConnection {
            newBuild()
                .forTasks(task)
                .addProgressListener(events, OperationType.TASK)
                .run()
        }
        events
    }
}
