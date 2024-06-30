package com.nmp.uts_anmp.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.nmp.uts_anmp.util.DB_NAME
import com.nmp.uts_anmp.util.MIGRATION_1_2
import com.nmp.uts_anmp.util.MIGRATION_2_3

@Database(entities = [Hobby::class, User::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hobbyDao(): HobbyDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME)
                .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                .build()
        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
