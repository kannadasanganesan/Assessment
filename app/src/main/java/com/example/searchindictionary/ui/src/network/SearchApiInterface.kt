package com.example.searchindictionary.ui.src.network

import com.example.searchindictionary.ui.src.data.models.Searchresultmodel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiInterface {

    @GET("software/acromine/dictionary.py?")
    suspend fun getSearchResult(@Query("sf")searchQuery: String): Response<MutableList<Searchresultmodel>>

}