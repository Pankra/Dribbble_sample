package test.example.dribbblesample.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import test.example.dribbblesample.ShotItem
import java.util.*

class ShotsAdapter(listener: ShotsDelegateAdapter.OnViewSelectedListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.SHOT, ShotsDelegateAdapter(listener))
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addShots(shots: List<ShotItem>) {
        val initPosition = items.size - 1
        // insert shots before the loading at the end of the list
        items.addAll(initPosition, shots)
        notifyItemRangeInserted(initPosition, shots.size)
    }

    fun clearAndAddShots(shots: List<ShotItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(shots)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getShots(): List<ShotItem> =
            items.filter { it.getViewType() == AdapterConstants.SHOT }
                    .map { it as ShotItem }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}