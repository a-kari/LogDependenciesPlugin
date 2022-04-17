package jp.neechan.akari.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class LogDependenciesPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register(LOG_DEPENDENCIES_TASK, LogDependenciesTask::class.java)
    }

    companion object {
        private const val LOG_DEPENDENCIES_TASK = "logDependencies"
    }
}
