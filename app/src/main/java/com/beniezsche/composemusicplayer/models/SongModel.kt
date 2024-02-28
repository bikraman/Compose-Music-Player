package com.beniezsche.composemusicplayer.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class SongModel(
    var id: Int,
    val status: String?,
    val sort : String?,
    val userCreated: String?,
    val dateCreated: String?,
    val userUpdated: String?,
    val dateUpdated: String?,
    var name: String?,
    val artist: String?,
    val accent: String?,
    val cover: String?,
    val isTopTrack: Boolean = false,
    val url: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(status)
        parcel.writeString(sort)
        parcel.writeString(userCreated)
        parcel.writeString(dateCreated)
        parcel.writeString(userUpdated)
        parcel.writeString(dateUpdated)
        parcel.writeString(name)
        parcel.writeString(artist)
        parcel.writeString(accent)
        parcel.writeString(cover)
        parcel.writeByte(if (isTopTrack) 1 else 0)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SongModel> {
        override fun createFromParcel(parcel: Parcel): SongModel {
            return SongModel(parcel)
        }

        override fun newArray(size: Int): Array<SongModel?> {
            return arrayOfNulls(size)
        }
    }
}