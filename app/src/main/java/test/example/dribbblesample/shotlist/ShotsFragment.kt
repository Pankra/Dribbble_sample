package test.example.dribbblesample.shotlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shots_fragment.*
import test.example.dribbblesample.R
import test.example.dribbblesample.inflate

class ShotsFragment : Fragment() {
    private val shotList by lazy {
        shots_recycler
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.shots_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shotList.layoutManager = LinearLayoutManager(context)
    }
}