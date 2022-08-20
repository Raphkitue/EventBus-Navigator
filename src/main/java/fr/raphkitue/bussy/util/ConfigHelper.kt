package fr.raphkitue.bussy.util

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.Project.DIRECTORY_STORE_FOLDER
import java.io.File


/**
 * # ConfigHelper
 * Created by 11324.
 * Date: 2019/10/8
 */
object ConfigHelper {
    //配置目录
    private lateinit var projectConfigPath: String

    @Synchronized
    fun init(project: Project) {
        if (!ConfigHelper::projectConfigPath.isInitialized) {
            projectConfigPath = project.basePath + "/" + DIRECTORY_STORE_FOLDER + "/EventBus-Navigator/"
            init()
        }
    }

    private fun init() {
        File(projectConfigPath).apply {
            if (!exists()) mkdirs()
        }
        postFile.apply {
            if (exists()) {
                readLines().forEach {
                    postMethodSet.add(it)
                }
            }
        }
    }

    private val postFile get() = File(projectConfigPath + "post-methods")

    //post 函数列表
    var postMethodSet = mutableSetOf(
            "tv.make.playout.event.bus.ScheduleEventBus.post",
            "tv.make.playout.event.bus.ScheduleEventBus.postAsync"
    )
        set(value) {
            field = value
            save()
        }

    private fun save() {
        postFile.apply {
            writeText(postMethodSet.joinToString("\n", "", ""))
        }

    }
}