package com.example.networking2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var homework1: Button
    lateinit var homework2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homework1 = findViewById(R.id.homework_1)
        homework2 = findViewById(R.id.homework_2)
    }

    fun buttonOnClick(view: View){
        val intent: Intent? = when(view){
            homework1 -> Intent(this,HomeworkActivity1::class.java)
            homework2 -> Intent(this, HomeworkActivity2::class.java)
            else -> null
        }
        if (intent != null)
            startActivity(intent)
    }
}