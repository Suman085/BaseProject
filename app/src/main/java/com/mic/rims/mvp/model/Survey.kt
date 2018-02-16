package com.mic.rims.mvp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Suman on 2/16/2018.
 */
@Entity(tableName = "surveys")
class Survey {

    @PrimaryKey(autoGenerate = false)
    var survey_id: Int? = null
    @ColumnInfo(name = "beneficiaryName")
    var beneficiaryName: String? = null
    @ColumnInfo(name = "beneficiaryPermanentAddress")
    var beneficiaryPermanentAddress: String? = null
    @ColumnInfo(name = "nagarpalika")
    var nagarpalika: String? = null
    @ColumnInfo(name = "ward")
    var ward: String? = null
    @ColumnInfo(name = "tole")
    var tole: String? = null
    @ColumnInfo(name = "age")
    var age: String? = null
    @ColumnInfo(name = "gender")
    var gender: String? = null
    @ColumnInfo(name = "ethnicity")
    var ethnicity: String? = null
    @ColumnInfo(name = "maritalStatus")
    var maritalStatus: String? = null
    @ColumnInfo(name = "fathersName")
    var fathersName: String? = null
    @ColumnInfo(name = "numberOfFamilyMembers")
    var numberOfFamilyMembers: String? = null
    @ColumnInfo(name = "occupation")
    var occupation: String? = null
    @ColumnInfo(name = "educationLevel")
    var educationLevel: String? = null
    @ColumnInfo(name = "contactNumber")
    var contactNumber: String? = null
    @Embedded
    var bankDetails: BankDetails? = null



}
