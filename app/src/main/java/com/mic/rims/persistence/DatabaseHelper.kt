package com.mic.rims.persistence

import android.arch.persistence.room.Room
import com.huluhive.travelsandtourism.database.UserDao
import com.mic.rims.MyApplication.Companion.context
import com.mic.rims.persistence.database.AppDatabase
import javax.inject.Singleton

/**
 * Created by Suman on 2/15/2018.
 */

@Singleton
class DatabaseHelper {
    init {
        createService()
    }

    fun getUserDao() = userDao

    companion object {

        private var appDatabase: AppDatabase? = null
        private var userDao: UserDao? = null
        private fun init() {
            userDao = appDatabase!!.userDao()

        }

        fun createService() {
            appDatabase=Room.databaseBuilder(context!!, AppDatabase::class.java, "my_application").allowMainThreadQueries().build()
            init()
        }
    }

}
