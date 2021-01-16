package com.infix.album.api

import com.infix.album.models.AlbumPhoto
import com.infix.album.models.User
import com.infix.album.models.UserAlbum
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface AppApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("albums")
    fun getUserAlbums(@Query("userId") userId: Int): Call<List<UserAlbum>>

    @GET("photos")
    fun getAlbumPictures(@Query("albumId") albumId: Int): Call<List<AlbumPhoto>>

    companion object {
        operator fun invoke(): AppApi = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)
    }


}