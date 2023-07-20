package me.kishankumar.newsfeed

import kotlinx.coroutines.runBlocking
import me.kishankumar.newsfeed.data.NewsRepo
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun apiTest() {
        val response = runBlocking {
            NewsRepo.getNews("sports","in",10,1)
        }

        assertEquals(response?.size, 1)
    }
}