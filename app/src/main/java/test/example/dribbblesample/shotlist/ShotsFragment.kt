package test.example.dribbblesample.shotlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.shots_fragment.*
import test.example.dribbblesample.*
import test.example.dribbblesample.adapter.AdapterConstants
import test.example.dribbblesample.adapter.ShotsAdapter
import test.example.dribbblesample.adapter.ShotsDelegateAdapter

class ShotsFragment : Fragment(), ShotsDelegateAdapter.OnViewSelectedListener {
    override fun onItemSelected(id: Int) {
        Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show()
    }

//    @Inject lateinit var shotsRepository: ShotsRepository
    private val shotList by lazy {
        shots_recycler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DribbbleApp.graph.inject(this)
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

        var shots = (1..10).map {
            val images = Images(null, "https://unsplash.it/200/300?image=$it", "https://unsplash.it/200/300?image=$it")
            val user = User(it, "User$it", "username$it")
            ShotItem(it, "Title$it", "Description$it", images, user)
        }
//        shots = shotsRepositoryImpl.getShots()
        (shotList.adapter as ShotsAdapter).addShots(shots)
    }
}