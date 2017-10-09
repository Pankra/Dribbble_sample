package test.example.dribbblesample.shotdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shot_details_fragment.*
import test.example.dribbblesample.R
import test.example.dribbblesample.ShotItem
import test.example.dribbblesample.inflate
import test.example.dribbblesample.loadImg

class ShotDetailsFragment : Fragment() {

    companion object {
        const val ARGS_SHOT = "args_shot"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.shot_details_fragment)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = arguments.getParcelable<ShotItem>(ARGS_SHOT)
        detail_title.text = item.title
        detail_image.loadImg(item.images.normal)
        detail_user.text = item.user.name + " (" + item.user.username + ")"
        detail_description.text = Html.fromHtml(item.description)
    }
}