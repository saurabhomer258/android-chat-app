package com.example.chatflow.di

import android.content.Context
import androidx.room.Room
import com.example.chatflow.data.local.AppDatabase
import com.example.chatflow.data.local.ChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "chat_db").build()
    
    @Provides
    fun provideChatDao(db: AppDatabase): ChatDao = db.chatDao()
}
