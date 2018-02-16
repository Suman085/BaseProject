package com.mic.rims.mvp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Suman on 2/15/2018.
 */
@Entity(tableName = "user")
class User {

    @PrimaryKey(autoGenerate = false)
    var id: Int? = null
    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "phone")
    var phone: String? = null
    @ColumnInfo(name = "username")
    var username: String? = null
    @ColumnInfo(name = "location")
    var location: String? = null
    @ColumnInfo(name = "designation")
    var designation: String? = null
    @ColumnInfo(name = "organization")
    var organization: String? = null
}
