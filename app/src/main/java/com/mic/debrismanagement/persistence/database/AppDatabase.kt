package com.mic.debrismanagement.persistence.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mic.debrismanagement.mvp.model.User

/**
 * Created by User on 8/22/2017.
 */
@Database(entities = [(User::class)], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}