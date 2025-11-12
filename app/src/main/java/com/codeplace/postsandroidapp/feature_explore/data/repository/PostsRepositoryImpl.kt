package com.codeplace.postsandroidapp.feature_explore.data.repository

import com.codeplace.postsandroidapp.feature_explore.data.remote.util.HttpRoutes
import com.codeplace.postsandroidapp.core.domain.NetworkError
import com.codeplace.postsandroidapp.core.domain.Result
import com.codeplace.postsandroidapp.feature_explore.data.remote.dto.CommentDto
import com.codeplace.postsandroidapp.feature_explore.data.remote.dto.PostDto
import com.codeplace.postsandroidapp.feature_explore.data.remote.mappers.toDomain
import com.codeplace.postsandroidapp.feature_explore.domain.model.Comment
import com.codeplace.postsandroidapp.feature_explore.domain.model.Post
import com.codeplace.postsandroidapp.feature_explore.domain.repository.PostsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.io.IOException
import java.net.UnknownHostException

class PostsRepositoryImpl(
    private val api: HttpClient,
) : PostsRepository {

    override suspend fun getPosts(): Result<List<Post>, NetworkError> {
        return try {
            val response = api.get(urlString = HttpRoutes.POSTS)
            when (response.status.value) {
                in 200..299 -> {
                    val postsDto = response.body<List<PostDto>>()
                    Result.Success(postsDto.map { it.toDomain() })
                }

                401 -> Result.Error(NetworkError.UNAUTHORIZED)
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                409 -> Result.Error(NetworkError.CONFLICT)
                413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
                else -> {
                    Result.Error(NetworkError.UNKNOWN)
                }
            }

        } catch (e: UnknownHostException) {
            Result.Error(NetworkError.UNABLE_TO_CONNECT)
        } catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getCommentsByPostId(postId: Int): Result<List<Comment>, NetworkError> {
        return try {
            val response = api.get(urlString = HttpRoutes.getPostCommentsByPostId(postId = postId))
            when (response.status.value) {
                in 200..299 -> {
                    val commentDto = response.body<List<CommentDto>>()
                    Result.Success(commentDto.map { it.toDomain() })
                }

                401 -> Result.Error(NetworkError.UNAUTHORIZED)
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                409 -> Result.Error(NetworkError.CONFLICT)
                413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
                else -> {
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: UnknownHostException) {
            Result.Error(NetworkError.UNABLE_TO_CONNECT)
        } catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET)
        }
    }

    override suspend fun getPostByPostId(postId: Int): Result<Post, NetworkError> {
        return try {
            val response = api.get(urlString = HttpRoutes.getPost(postId = postId))
            when (response.status.value) {
                in 200..299 -> {
                    val postDto = response.body<PostDto>()
                    Result.Success(postDto.toDomain())
                }
                401 -> Result.Error(NetworkError.UNAUTHORIZED)
                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                409 -> Result.Error(NetworkError.CONFLICT)
                413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
                else -> {
                    Result.Error(NetworkError.UNKNOWN)
                }
            }
        }catch (e:UnknownHostException){
            Result.Error(NetworkError.UNABLE_TO_CONNECT)
        }catch (e:IOException){
            Result.Error(NetworkError.NO_INTERNET)
    }
}
}