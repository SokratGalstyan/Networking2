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

        val data:PhotoModel? = PhotoModel()

        val recycler = findViewById<RecyclerView>(R.id.photos)


        GlobalScope.launch(Dispatchers.IO) {
            //@GET response
            //val data = Photo.retrofit.downLoadPhoto().execute().body()

            //@POST response
            for (i in 1..20) {
                val post = PostPhotoModel.PostPhotoModelItem("Title $i", "https://picsum.photos/id/$i/200/200")
                val response = Photo.retrofit.upLoadPhoto(post).execute().body()
                if (response != null)
                    data?.add(response)
            }

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
