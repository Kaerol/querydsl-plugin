/*
 * Copyright 2015 the original author or authors.
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
package com.ewerk.gradle.plugins.querydsl.tasks

import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.compile.JavaCompile

/**
 * Compiles the meta model using querydsl annotation processors supplied by the querydsl extension
 * configuration.
 *
 * @author holgerstolzenberg
 * @author griffio
 * @since 1.0.3
 */
@SuppressWarnings("JavaStylePropertiesInvocation")
class QuerydslCompile extends JavaCompile {

  QuerydslCompile() {
    setSource(project.sourceSets.main.java)

    if (project.plugins.hasPlugin(WarPlugin.class)) {
      project.configurations {
        querydsl.extendsFrom compile, providedRuntime, providedCompile
      }
    } else {
      project.configurations {
        querydsl.extendsFrom compile
      }
    }

    project.afterEvaluate {
      setClasspath(project.configurations.querydsl)
      File file = project.file(project.querydsl.querydslSourcesDir)
      setDestinationDir(file)
    }
  }
}
