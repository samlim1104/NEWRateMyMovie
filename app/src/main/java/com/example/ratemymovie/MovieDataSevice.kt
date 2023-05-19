package com.example.ratemymovie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDataSevice {
    @GET(".")
    fun getMovieDataByTitle(@Query("s") name: String, @Query("apikey") apikey: String) : Call<MovieWrapper>
}

// baseUrl + get + ?s=name&apikey=blah
