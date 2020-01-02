package com.example.banksimulatorandroid.Model.Response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserRest constructor(

    @SerializedName("userId")
    val userId: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("balance")
    val balance: Double,

    @SerializedName("accountNumber")
    val accountNumber: String
    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(email)
        parcel.writeDouble(balance)
        parcel.writeString(accountNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserRest> {
        override fun createFromParcel(parcel: Parcel): UserRest {
            return UserRest(parcel)
        }

        override fun newArray(size: Int): Array<UserRest?> {
            return arrayOfNulls(size)
        }
    }
}