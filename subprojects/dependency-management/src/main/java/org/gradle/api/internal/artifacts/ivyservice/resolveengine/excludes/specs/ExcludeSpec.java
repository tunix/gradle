/*
 * Copyright 2019 the original author or authors.
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
package org.gradle.api.internal.artifacts.ivyservice.resolveengine.excludes.specs;

import org.gradle.api.artifacts.ModuleIdentifier;
import org.gradle.internal.component.model.IvyArtifactName;

public interface ExcludeSpec {
    /**
     * Determines if this exclude rule excludes the supplied module.
     */
    boolean excludes(ModuleIdentifier module);

    /**
     * Determines if this exclude rule excludes the supplied artifact, for the specified module.
     */
    boolean excludesArtifact(ModuleIdentifier module, IvyArtifactName artifactName);

    /**
     * Tells if this rule may exclude some artifacts. This is used to optimize artifact resolution.
     */
    boolean mayExcludeArtifacts();

    /**
     * This method is used to determine if 2 excludes are equivalent, only differing by the artifact they exclude.
     * This is used by the resolution engine to figure out if it should invalidate a previous visit or not.
     */
    boolean equalsIgnoreArtifact(ExcludeSpec other);

}
