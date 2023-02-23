package com.example.searchindictionary.ui.src.data.models

import com.google.gson.annotations.SerializedName


data class Vars (

  @SerializedName("lf"    ) var lf    : String? = null,
  @SerializedName("freq"  ) var freq  : Int?    = null,
  @SerializedName("since" ) var since : Int?    = null

)