package com.example.searchindictionary.ui.src.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserService {

    val apiService: SearchApiInterface

    //http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
    private val BASE_URL = "http://www.nactem.ac.uk/";

    init {

        val client = OkHttpClient.Builder().build()
        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SearchApiInterface::class.java)
    }
}