package com.example.ratemymovie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class MovieData(
    @SerializedName("Title")
    var name: String = "name",
    var maturityRating: String = "PG",
    @SerializedName("Year")
    var year: Int = 1996,
    var runtime: Int = 121,
    var genre: String = "genre",
    var plot: String = "plot",
    var rating: Float = 4.5f,
    var ownerId: String? = null,
    var objectId: String? = null

):Parcelable
