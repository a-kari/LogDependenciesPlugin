package jp.neechan.akari.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class LogDependenciesTask : DefaultTask() {

    @Input
    var configuration: Collection<String> = mutableListOf()

    @TaskAction
    fun createFileAndLogDependencies() {
        val dependenciesFile = getDependenciesFile()
        logDependencies(dependenciesFile)
    }

    private fun getDependenciesFile(): File {
        val dependenciesFile = File(DEPENDENCIES_FILE_PATH)

        if (!dependenciesFile.exists()) {
            println("$DEPENDENCIES_FILE_PATH does not exist. Creating folder...")

            val dependenciesDirectory = File(DEPENDENCIES_DIRECTORY_PATH)
            if (!dependenciesDirectory.exists()) {
                dependenciesDirectory.mkdir()
            }
            dependenciesFile.createNewFile()
        }
        return dependenciesFile
    }

    private fun logDependencies(file: File) {
        file.writeText(getFormattedDateTime(), Charsets.UTF_8)
        file.appendText("\n====Dependencies====\n", Charsets.UTF_8)

        configuration.forEach {
            project.configurations.getByName(it).dependencies.forEach { dependency ->
                file.appendText(
                    "${dependency.group}:${dependency.name}:${dependency.version}\n",
                    Charsets.UTF_8
                )
            }
        }

        file.appendText("====================", Charsets.UTF_8)
        file.appendText("\n\n\n", Charsets.UTF_8)
    }

    private fun getFormattedDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return current.format(formatter)
    }

    companion object {
        private const val DEPENDENCIES_DIRECTORY_PATH = "dependencies"
        private const val DEPENDENCIES_FILE_NAME = "Dependencies.txt"
        private const val DEPENDENCIES_FILE_PATH = "$DEPENDENCIES_DIRECTORY_PATH/$DEPENDENCIES_FILE_NAME"
    }
}
