package com.example.networking2

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.net.URL

class RecyclerAdapter(val context: Context,val data: PhotoModel?): RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {


    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val photo: ImageView
        val id: TextView
        val title: TextView

        init {
            photo = view.findViewById(R.id.photo)
            id = view.findViewById(R.id.id_text)
            title = view.findViewById(R.id.title_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val current = data?.get(position)
        if (current != null) {
            Picasso.get().load(current.url).error(R.drawable.error).into(holder.photo)
            holder.id.text = (current.id).toString()
            holder.title.text = current.title
        }

    }

    override fun getItemCount(): Int {
        return  data?.size ?: 0
    }
}