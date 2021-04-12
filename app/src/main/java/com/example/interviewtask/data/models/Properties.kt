package com.example.interviewtask.data.models

import com.google.gson.annotations.SerializedName


data class Properties(
    @SerializedName("description")val description: String,
    @SerializedName("id")val id: Int,
    @SerializedName("marker-color")val marker_color: String,
    @SerializedName("marker-size")val marker_size: String,
    @SerializedName("occurred_at")val occurred_at: String,
    @SerializedName("title")val title: String
)