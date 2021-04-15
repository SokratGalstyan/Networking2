package com.example.networking2


class PhotoModel(): ArrayList<PhotoModel.PhotoModelItem>(){
    data class PhotoModelItem(
            val albumId: Int,
            val id: Int,
            val thumbnailUrl: String,
            val title: String,
            val url: String
    )
    data class PostPhotoModelItem(
            val id: Int
    )
}





