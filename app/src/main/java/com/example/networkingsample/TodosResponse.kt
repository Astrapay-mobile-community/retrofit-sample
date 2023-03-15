package com.example.networkingsample


data class TodosResponse(
    val id: String,
    val userId: String,
    val title: String,
    val completed: Boolean
)
