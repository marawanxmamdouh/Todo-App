package dev.marawanxmamdouh.todo.statistics

import dev.marawanxmamdouh.todo.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // Given an active tasks (the false makes this active)
        val tasks = listOf(
            Task("title", "desc", isCompleted = false)
        )
        // When call our function
        val result = getActiveAndCompletedStats(tasks)

        // Then Check the result
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_Completed_returnsFiftyFifty() {
        // Given tasks (the false makes this active)
        val tasks = listOf(
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = true),
        )
        // When call our function
        val result = getActiveAndCompletedStats(tasks)

        // Then check the result
        assertThat(result.completedTasksPercent, `is`(50f))
        assertThat(result.activeTasksPercent, `is`(50f))
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        // Given an active tasks (the false makes this active)
        val tasks = listOf<Task>()

        // When call our function
        val result = getActiveAndCompletedStats(tasks)

        // Then check the result
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        // Given an active tasks (the false makes this active)
        val tasks = null

        // When call our function
        val result = getActiveAndCompletedStats(tasks)

        // Then check the result
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }
}