package com.codeplace.postsandroidapp.feature_explore.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codeplace.postsandroidapp.feature_explore.domain.models.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao{

    @Query("SELECT * FROM post")
    fun getAll(): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postEntity: PostEntity)

    @Delete
    fun delete(postEntity: PostEntity)
}