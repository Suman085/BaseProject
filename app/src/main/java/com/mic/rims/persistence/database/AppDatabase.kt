package com.mic.rims.persistence.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.huluhive.travelsandtourism.database.UserDao
import com.mic.rims.mvp.model.User

/**
 * Created by User on 8/22/2017.
 */
@Database(entities = arrayOf(User::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}