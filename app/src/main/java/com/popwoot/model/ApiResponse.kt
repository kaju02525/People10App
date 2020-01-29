package com.popwoot.model
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("rows")
    var rows: List<Row> = listOf(),
    @SerializedName("title")
    var title: String? = null
)

data class Row(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("imageHref")
    var imageHref: String? = null,
    @SerializedName("title")
    var title: String? = null
)