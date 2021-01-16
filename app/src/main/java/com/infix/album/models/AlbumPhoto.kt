package com.infix.album.models

data class AlbumPhoto (
    val albumID: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)