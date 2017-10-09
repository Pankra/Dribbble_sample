package test.example.dribbblesample.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shot_item.view.*
import test.example.dribbblesample.R
import test.example.dribbblesample.ShotItem
import test.example.dribbblesample.inflate
import test.example.dribbblesample.loadImg

class ShotsDelegateAdapter(val viewActions: OnViewSelectedListener) : ViewTypeDelegateAdapter {

    interface OnViewSelectedListener {
        fun onItemSelected(item: ShotItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ShotsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) =
            (holder as ShotsViewHolder).bind(item as ShotItem)

    inner class ShotsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.shot_item)) {

        fun bind(item: ShotItem) {
            itemView.image.loadImg(item.images.teaser)
            itemView.title.text = item.title

            super.itemView.setOnClickListener { viewActions.onItemSelected(item)}
        }
    }
}