package com.example.searchindictionary.ui.src.data.models

import com.google.gson.annotations.SerializedName


data class Searchresultmodel (

  @SerializedName("sf"  ) var sf  : String?        = null,
  @SerializedName("lfs" ) var lfs : ArrayList<Lfs> = arrayListOf()

)