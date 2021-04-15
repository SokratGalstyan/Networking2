package com.example.networking2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeworkActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework1)
        val context = this
        val recycler = findViewById<RecyclerView>(R.id.photos)

        val post = PhotoModel.PostPhotoModelItem(1)


        GlobalScope.launch(Dispatchers.IO) {
            val data = Photo.retrofit.create(PhotoApiService::class.java).loadPhoto().execute().body()

            withContext(Dispatchers.Main){
                if (data != null){
                    val adapter = RecyclerAdapter(context, data)
                    recycler.adapter = adapter
                    recycler.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
}
