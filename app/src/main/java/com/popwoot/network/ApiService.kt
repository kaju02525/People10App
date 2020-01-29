package com.popwoot.network


import com.popwoot.model.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getAsync(): Deferred<Response<ApiResponse>>
}