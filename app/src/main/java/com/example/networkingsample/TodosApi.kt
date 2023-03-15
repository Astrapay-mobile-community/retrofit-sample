package com.example.networkingsample

import retrofit2.Response
import retrofit2.http.GET

interface TodosApi {
    @GET("/todossa?_sort=id&_order=desc&_limit=10")
    suspend fun getTodos(): Response<Array<TodosResponse>>
}
