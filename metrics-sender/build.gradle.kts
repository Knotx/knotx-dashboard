/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.nosphere.apache.rat.RatTask

plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
    id("jacoco")
    id("org.nosphere.apache.rat") version "0.4.0"
}

// -----------------------------------------------------------------------------
// Dependencies
// -----------------------------------------------------------------------------
dependencies {
    implementation(platform("io.knotx:knotx-dependencies:${project.version}"))
    implementation(group = "io.vertx", name = "vertx-core")
    implementation(group = "io.vertx", name = "vertx-rx-java2")
    implementation(group = "io.vertx", name = "vertx-codegen")
    implementation(group = "io.vertx", name = "vertx-dropwizard-metrics")
    implementation(group = "io.dropwizard.metrics", name = "metrics-graphite", version = "4.0.2")
}

// -----------------------------------------------------------------------------
// Source sets
// -----------------------------------------------------------------------------
sourceSets.named("main") {
    java.srcDir("src/main/generated")
}

// -----------------------------------------------------------------------------
// Tasks
// -----------------------------------------------------------------------------
tasks {
    named<RatTask>("rat") {
        excludes.addAll("**/*.json", "**/*.md", "**/*.adoc", "**/build/*", "**/out/*", "**/generated/*")
    }
    getByName("build").dependsOn("rat")
}

apply(from = "../gradle/codegen.deps.gradle.kts")
