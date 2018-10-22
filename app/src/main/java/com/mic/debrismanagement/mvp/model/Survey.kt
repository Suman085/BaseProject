package com.mic.debrismanagement.mvp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Suman on 2/16/2018.
 */
@Entity(tableName = "surveys")
class Survey() : Parcelable {

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
    @ColumnInfo(name = "beneficiaryImage")
    var beneficiaryImage: String? = null
    @ColumnInfo(name = "houseImage")
    var houseImage: String? = null
    @ColumnInfo(name = "nissaImage")
    var nissaImage: String? = null
    @ColumnInfo(name = "redCardImage")
    var redCardImage: String? = null

    constructor(parcel: Parcel) : this() {
        survey_id = parcel.readValue(Int::class.java.classLoader) as? Int
        beneficiaryName = parcel.readString()
        beneficiaryPermanentAddress = parcel.readString()
        nagarpalika = parcel.readString()
        ward = parcel.readString()
        tole = parcel.readString()
        age = parcel.readString()
        gender = parcel.readString()
        ethnicity = parcel.readString()
        maritalStatus = parcel.readString()
        fathersName = parcel.readString()
        numberOfFamilyMembers = parcel.readString()
        occupation = parcel.readString()
        educationLevel = parcel.readString()
        contactNumber = parcel.readString()
        beneficiaryImage = parcel.readString()
        houseImage = parcel.readString()
        nissaImage = parcel.readString()
        redCardImage = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(survey_id)
        parcel.writeString(beneficiaryName)
        parcel.writeString(beneficiaryPermanentAddress)
        parcel.writeString(nagarpalika)
        parcel.writeString(ward)
        parcel.writeString(tole)
        parcel.writeString(age)
        parcel.writeString(gender)
        parcel.writeString(ethnicity)
        parcel.writeString(maritalStatus)
        parcel.writeString(fathersName)
        parcel.writeString(numberOfFamilyMembers)
        parcel.writeString(occupation)
        parcel.writeString(educationLevel)
        parcel.writeString(contactNumber)
        parcel.writeString(beneficiaryImage)
        parcel.writeString(houseImage)
        parcel.writeString(nissaImage)
        parcel.writeString(redCardImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Survey> {
        override fun createFromParcel(parcel: Parcel): Survey {
            return Survey(parcel)
        }

        override fun newArray(size: Int): Array<Survey?> {
            return arrayOfNulls(size)
        }
    }


}
