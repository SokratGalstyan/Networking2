package com.example.networking2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeworkActivity2 : AppCompatActivity() {
    val news = arrayListOf<Article?>(null)
    var isLoading = true
    var nextPage = 1
    val map = mapOf("apiKey" to "f25e73af7c7c46e58ca3fc4aa7e9fd48", "q" to "tesla")
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework2)
        recyclerView = findViewById<RecyclerView>(R.id.news)

        val adapter = NewsRecyclerAdapter(this, news)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadNews()

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading && layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == news.size - 1)
                {
                    loadNews()
                    isLoading = true
                    news.add(null)
                    adapter.notifyItemInserted(news.size - 1)
                }
            }
        })

        recyclerView.setOnClickListener {

        }
    }

    fun loadNews(){
        GlobalScope.launch(Dispatchers.Default){
            val array = NewsResponse.retrofit.getNews(map, mapOf("pageSize" to 20, "page" to nextPage)).execute().body()?.articles ?: arrayListOf()


            withContext(Dispatchers.Main){
                news.removeLast()
                recyclerView.adapter?.notifyItemRemoved(news.size)
                news.addAll(array)
                recyclerView.adapter?.notifyDataSetChanged()
                isLoading = false
                nextPage++
            }
        }
    }
}