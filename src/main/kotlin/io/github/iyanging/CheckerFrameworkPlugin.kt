package io.github.iyanging

import org.gradle.api.Plugin
import org.gradle.api.Project

class CheckerFrameworkPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("hello") { task -> task.doLast { println("hello, world") } }
    }
}
