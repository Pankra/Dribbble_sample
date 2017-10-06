package test.example.dribbblesample

import android.os.Parcel
import android.os.Parcelable
import test.example.dribbblesample.adapter.AdapterConstants
import test.example.dribbblesample.adapter.ViewType

data class ShotItem(
        val id: Int,
        val title: String,
        val description: String,
        val images: Images,
        val user: User

) : ViewType, Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Images::class.java.classLoader),
            parcel.readParcelable(User::class.java.classLoader)) {
    }

    override fun getViewType() = AdapterConstants.SHOT

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeParcelable(images, flags)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShotItem> {
        override fun createFromParcel(parcel: Parcel): ShotItem {
            return ShotItem(parcel)
        }

        override fun newArray(size: Int): Array<ShotItem?> {
            return arrayOfNulls(size)
        }
    }

}

data class Images(
        val hidpi: String?,
        val normal: String,
        val teaser: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hidpi)
        parcel.writeString(normal)
        parcel.writeString(teaser)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Images> {
        override fun createFromParcel(parcel: Parcel): Images {
            return Images(parcel)
        }

        override fun newArray(size: Int): Array<Images?> {
            return arrayOfNulls(size)
        }
    }
}

data class User(
        val id: Int,
        val name: String,
        val username: String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}