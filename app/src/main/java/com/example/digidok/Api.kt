package com.example.digidok

import android.telecom.Call
import retrofit2.http.GET

interface Api {
    @GET("https://jsonplaceholder.typicode.com/posts")

    fun getPost():Call<ArrayList<PostRespond>>
}