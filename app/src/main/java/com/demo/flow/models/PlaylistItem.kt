package com.demo.flow.models

import com.google.gson.annotations.SerializedName

data class PlaylistItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
