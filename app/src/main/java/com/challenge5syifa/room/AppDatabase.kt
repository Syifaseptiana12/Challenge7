package com.challenge5syifa.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.challenge5syifa.room.dao.UserDao
import com.challenge5syifa.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object{
        fun db(ctx:Context): AppDatabase {
            return Room.databaseBuilder(
                ctx,
                AppDatabase::class.java, "Movie"
            )
            .allowMainThreadQueries()
            .build()
        }
    }
}