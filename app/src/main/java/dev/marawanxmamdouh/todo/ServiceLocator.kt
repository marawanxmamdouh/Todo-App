package dev.marawanxmamdouh.todo

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource
import dev.marawanxmamdouh.todo.data.source.DefaultTasksRepository
import dev.marawanxmamdouh.todo.data.source.TasksDataSource
import dev.marawanxmamdouh.todo.data.source.TasksRepository
import dev.marawanxmamdouh.todo.data.source.local.TasksLocalDataSource
import dev.marawanxmamdouh.todo.data.source.local.ToDoDatabase
import kotlinx.coroutines.runBlocking

object ServiceLocator {

    private val lock = Any()

    private var database: ToDoDatabase? = null
        @VisibleForTesting set

    @Volatile
    var tasksRepository: TasksRepository? = null
    fun provideTasksRepository(context: Context): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): TasksRepository {
        val newRepo =
            DefaultTasksRepository(TasksRemoteDataSource, createTaskLocalDataSource(context))
        tasksRepository = newRepo
        return newRepo
    }

    private fun createTaskLocalDataSource(context: Context): TasksDataSource {
        val database = database ?: createDataBase(context)
        return TasksLocalDataSource(database.taskDao())
    }

    private fun createDataBase(context: Context): ToDoDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java, "Tasks.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                TasksRemoteDataSource.deleteAllTasks()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            tasksRepository = null
        }
    }
}