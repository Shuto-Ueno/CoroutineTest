package com.example.coroutinetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("hello")

        val a = GlobalScope.async { lazy(4, 2000) }
        val b = GlobalScope.async { lazy(6, 3000) }

        // 2秒かけてaを、3秒かけてbを取得するためhelloの3秒後にa + b = 10が表示される
        runBlocking {
            println("a + b = %d".format(a.await() + b.await()))
        }

        println("world")
    }

    private suspend fun lazy(n: Int, timeMills: Long): Int {
        delay(timeMills)
        return n
    }
}