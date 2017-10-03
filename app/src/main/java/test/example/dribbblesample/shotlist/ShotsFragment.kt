package test.example.dribbblesample.shotlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.shots_fragment.*
import test.example.dribbblesample.R
import test.example.dribbblesample.ShotItem
import test.example.dribbblesample.adapter.AdapterConstants
import test.example.dribbblesample.adapter.ShotsAdapter
import test.example.dribbblesample.adapter.ShotsDelegateAdapter
import test.example.dribbblesample.inflate

class ShotsFragment : Fragment(), ShotsDelegateAdapter.OnViewSelectedListener {
    override fun onItemSelected(id: Int) {
        Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show()
    }

    private val shotList by lazy {
        shots_recycler
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.shots_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (shotList.adapter == null) {
            shotList.adapter = ShotsAdapter(this)
        }

        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (shotList.adapter.getItemViewType(position)) {
                    AdapterConstants.SHOT -> return 1
                    AdapterConstants.LOADING -> return 2
                }
                return -1
            }
        }

        shotList.layoutManager = layoutManager

        val shots = (1..10).map { ShotItem(it, "https://unsplash.it/200/300?image=$it") }
        (shotList.adapter as ShotsAdapter).addShots(shots)
    }
}