package com.codeplace.postsandroidapp.feature_explore.data.network

object HttpRoutes {

    fun fetchPosts(): String {
        return "posts/"
    }
    fun fetchPost(postId: Int): String {
        return "posts/$postId"
    }
    fun fetchComments(postId: Int): String {
        return "posts/$postId/comments"
    }
}