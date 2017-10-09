package test.example.dribbblesample.shotlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shots_fragment.*
import test.example.dribbblesample.*
import test.example.dribbblesample.adapter.AdapterConstants
import test.example.dribbblesample.adapter.ShotsAdapter
import test.example.dribbblesample.adapter.ShotsDelegateAdapter
import test.example.dribbblesample.di.ShotsRepository
import test.example.dribbblesample.interactor.Executor
import test.example.dribbblesample.interactor.ShotsInteractor
import test.example.dribbblesample.interactor.ShotsInteractorImpl
import test.example.dribbblesample.shotdetails.ShotDetailsFragment
import javax.inject.Inject

class ShotsFragment : Fragment(), ShotsDelegateAdapter.OnViewSelectedListener {
    override fun onItemSelected(item: ShotItem) {
        val fragment = ShotDetailsFragment()
        val args = Bundle()
        args.putParcelable(ShotDetailsFragment.ARGS_SHOT, item)
        fragment.arguments = args
        (activity as MainActivity).changeFragment(fragment, true)
    }

    companion object {
        private val STATE_SHOTS = "state_shots"
    }

    @Inject lateinit var shotsRepository: ShotsRepository
    @Inject lateinit var threadsExecutor: Executor
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DribbbleApp.graph.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.shots_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (shots_recycler.adapter == null) {
            shots_recycler.adapter = ShotsAdapter(this)
        }

        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (shots_recycler.adapter.getItemViewType(position)) {
                    AdapterConstants.SHOT -> return 1
                    AdapterConstants.LOADING -> return 2
                }
                return -1
            }
        }

        shots_recycler.layoutManager = layoutManager
        shots_recycler.addOnScrollListener(InfiniteScrollListener({ requestShots() }, layoutManager))

        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_SHOTS)) {
            val shotList = savedInstanceState.getParcelableArrayList<ShotItem>(STATE_SHOTS)
            (shots_recycler.adapter as ShotsAdapter).clearAndAddShots(shotList)
        } else {
            requestShots()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val shots = (shots_recycler.adapter as ShotsAdapter).getShots()
        if (shots.isNotEmpty()) {
            outState.putParcelableArrayList(STATE_SHOTS, shots)
        }
    }

    private fun requestShots() {
        ShotsInteractorImpl(
                threadsExecutor,
                page,
                shotsRepository,
                object: ShotsInteractor.Callback {
                    override fun onShotsReceived(shots: List<ShotItem>) {
                        val shotsAdapter = shots_recycler.adapter as ShotsAdapter
                        shotsAdapter.addShots(shots)
                        page++
                        //TODO "something strange on load images: not refreshing?"
                    }
                }
        ).execute()
    }
}