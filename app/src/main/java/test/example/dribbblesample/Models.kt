package test.example.dribbblesample

import android.os.Parcel
import android.os.Parcelable
import test.example.dribbblesample.adapter.AdapterConstants
import test.example.dribbblesample.adapter.ViewType

data class ShotItem(
        val id: Int,
        val imageUrl: String
) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.SHOT

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ShotItem> = object : Parcelable.Creator<ShotItem> {
            override fun createFromParcel(source: Parcel): ShotItem = ShotItem(source)
            override fun newArray(size: Int): Array<ShotItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(imageUrl)
    }
}