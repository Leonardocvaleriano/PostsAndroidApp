package com.codeplace.mvvmpostsandroidapp.data.datasource

import com.codeplace.mvvmpostsandroidapp.data.network.mappers.toDomain
import com.codeplace.mvvmpostsandroidapp.data.network.models.PostDto
import com.codeplace.mvvmpostsandroidapp.data.network.utils.HttpRoutes
import com.codeplace.mvvmpostsandroidapp.data.network.utils.NetworkError
import com.codeplace.mvvmpostsandroidapp.data.network.utils.Result

import com.codeplace.mvvmpostsandroidapp.domain.models.Post
import com.codeplace.mvvmpostsandroidapp.domain.repositories.PostsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException

class PostRepositoryImpl (
    private val httpClient: HttpClient
):PostsRepository {
    override suspend fun getPosts(): Result<List<Post>, NetworkError> {

        val response = try {
            httpClient.get(urlString = HttpRoutes.POSTS)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        }catch (e: UnknownHostException){
            return Result.Error(NetworkError.UNABLE_TO_CONNECT)
        }
        catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when(response.status.value){
            in 200..299 -> {
                val postsResponse = response.body<List<PostDto>>()
                Result.Success(postsResponse.map { it.toDomain() })
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}