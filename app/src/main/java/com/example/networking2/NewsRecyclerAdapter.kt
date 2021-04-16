package com.example.networking2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class NewsRecyclerAdapter(val context: Context, val news: ArrayList<Article?>): RecyclerView.Adapter<RecyclerView.ViewHolder> (){
    var position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0){
            val view = LayoutInflater.from(context).inflate(R.layout.news_row, parent, false)
            return ItemViewHolder(view)
        }
        else{
            return LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.news_loading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is ItemViewHolder -> {
                val current = news[position]
                Glide.with(context).load(current?.urlToImage).placeholder(R.drawable.image)
                        .error(R.drawable.error).into(holder.image)
                holder.title.text = current?.title ?: "Unknown Title"
                holder.description.text = current?.description ?: "No Description"

                holder.title.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(news[position]?.url))
                    context.startActivity(intent)
                }

                holder.image.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(news[position]?.url))
                    context.startActivity(intent)
                }
            }
            is LoadingViewHolder -> {}
        }
    }

    override fun getItemCount(): Int {
        return  news.size
    }

    override fun getItemViewType(position: Int): Int = if(news[position] == null) 1 else 0


    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView
        val title: TextView
        val description: TextView

        init {
            image = view.findViewById(R.id.news_image)
            title = view.findViewById(R.id.news_title)
            description = view.findViewById(R.id.news_description)
        }
    }

    class LoadingViewHolder(view: View): RecyclerView.ViewHolder(view){
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
    }

}