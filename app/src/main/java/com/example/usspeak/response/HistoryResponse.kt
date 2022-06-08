package com.example.usspeak.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

    @field:SerializedName("items")
    val items: List<DataItem>
) {
    data class DataItem(

        @field:SerializedName("date")
        val date: String? = null,

        @field:SerializedName("duration")
        val duration: String? = null,

        @field:SerializedName("emotion")
        val emotion: String? = null,

        @field:SerializedName("suggestion")
        val suggestion: String? = null
    )
}


