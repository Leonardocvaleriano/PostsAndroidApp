package com.codeplace.postsandroidapp.feature_explore.domain.models

data class Comment(
    val postId:Int,
    val id:Int,
    val name:String,
    val email:String,
    val body:String
)
