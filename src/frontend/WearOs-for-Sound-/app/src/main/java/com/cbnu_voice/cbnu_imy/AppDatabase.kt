package com.cbnu_voice.cbnu_imy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cbnu_voice.cbnu_imy.Dao.ChatMessageDao
import com.cbnu_voice.cbnu_imy.Data.ChatEntity

@Database(entities = [ChatEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao

    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getDatabase(context: Context): ChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "Chat_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
