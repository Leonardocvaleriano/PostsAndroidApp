package com.codeplace.postsandroidapp.feature_explore.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("post")
data class PostEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "user_id") val userId: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "body") val body: String?,
)