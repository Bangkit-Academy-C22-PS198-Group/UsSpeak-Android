package com.example.usspeak.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class HistoryResponse(
    @SerializedName("emotion")
    var emotion: String? = null,

    @SerializedName("date")
    var dateTaken: Date,

    @SerializedName("duration")
    var duration: String,

    @SerializedName("suggestion")
    var suggestion: String? = null,

    @SerializedName("filename")
    var fileName: String,

    @SerializedName("data")
    var audioFile: String
): Parcelable
