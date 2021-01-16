package com.infix.album.models

import com.google.gson.annotations.SerializedName


data class UserAlbum(
    val userID: Int,
    val title: String
) {
    @SerializedName("id")
    val albumId: Int = 0
}
