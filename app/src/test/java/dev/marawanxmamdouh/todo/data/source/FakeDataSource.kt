package dev.marawanxmamdouh.todo.data.source

import androidx.lifecycle.LiveData
import dev.marawanxmamdouh.todo.data.Result
import dev.marawanxmamdouh.todo.data.Result.Error
import dev.marawanxmamdouh.todo.data.Result.Success
import dev.marawanxmamdouh.todo.data.Task

class FakeDataSource(private var tasks: MutableList<Task>? = mutableListOf()) : TasksDataSource {

    override suspend fun getTasks(): Result<List<Task>> {
        tasks?.let { return Success(ArrayList(it)) }
        return Error(
            Exception("Tasks not found")
        )
    }

    override suspend fun deleteAllTasks() {
        tasks?.clear()
    }

    override suspend fun saveTask(task: Task) {
        tasks?.add(task)
    }

    override fun observeTasks(): LiveData<Result<List<Task>>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTasks() {
        TODO("Not yet implemented")
    }

    override fun observeTask(taskId: String): LiveData<Result<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedTasks() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: String) {
        TODO("Not yet implemented")
    }
}