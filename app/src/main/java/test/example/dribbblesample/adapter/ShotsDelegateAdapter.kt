package test.example.dribbblesample.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shot_item.view.*
import test.example.dribbblesample.R
import test.example.dribbblesample.ShotItem
import test.example.dribbblesample.inflate
import test.example.dribbblesample.loadImg

class ShotsDelegateAdapter(val viewActions: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = NewsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as ShotItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.shot_item)) {

        private val image = itemView.image

        fun bind(item: ShotItem) {
            image.loadImg(item.imageUrl)

            super.itemView.setOnClickListener { viewActions.onItemSelected(item.id)}
        }
    }
}