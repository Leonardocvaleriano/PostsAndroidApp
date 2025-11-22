package com.codeplace.postsandroidapp.core.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostEntity
import com.codeplace.postsandroidapp.feature_explore.data.local.room.PostDao

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}