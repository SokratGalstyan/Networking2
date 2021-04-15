package com.example.networking2


class PhotoModel(): ArrayList<PhotoModel.PhotoModelItem>(){
    data class PhotoModelItem(
            val albumId: Int,
            val id: Int,
            val thumbnailUrl: String,
            val title: String,
            val url: String
    )

}

class PostPhotoModel(): ArrayList<PostPhotoModel.PostPhotoModelItem>(){
    data class PostPhotoModelItem(
            val title: String,
            val url: String
    )
}





