package com.example.socialapplicationexample.models

data class Post(
    val text:String = "",
    val createdBy: User = User(),
    val createdAt:Long = 0L,
    val likeBy:ArrayList<String> = ArrayList()

)