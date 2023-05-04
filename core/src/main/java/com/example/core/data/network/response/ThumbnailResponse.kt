package com.example.core.data.network.response

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    val path: String,
    @SerializedName(value = "extension")
    val extensionPath: String
)
