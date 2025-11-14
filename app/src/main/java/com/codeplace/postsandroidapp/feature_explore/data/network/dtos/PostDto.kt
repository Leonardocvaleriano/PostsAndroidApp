package com.codeplace.postsandroidapp.feature_explore.data.network.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val userId:Int,
    val id:Int,
    val title:String,
    val body:String,
    //val comments:List<CommentDto>
)
