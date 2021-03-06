package com.mic.debrismanagement.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mic.debrismanagement.R
import com.mic.debrismanagement.mvp.model.Survey
import com.mic.debrismanagement.utils.Constants
import kotlinx.android.synthetic.main.reconstruction_status_is_started.*

/**
 * Created by Suman on 2/17/2018.
 */
class ReconstructionStatusFragmentStarted : ReconstructionStatusFragment() {
    var survey:Survey?=null
    companion object {
        fun newInstance(survey: Survey?): ReconstructionStatusFragmentStarted {
            val bundle =Bundle()
            val fragment=ReconstructionStatusFragmentStarted()
            bundle.putParcelable(Constants.SURVEY,survey)
            fragment.arguments=bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments?.getParcelable<Survey>(Constants.SURVEY)
    }

    override fun getContentView() = R.layout.reconstruction_status_is_started

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question_1.setBackgroundResource(R.drawable.circular_shape)
        question_1.setQuestion("What is the gender")
        button_done.setOnClickListener {
            activity!!.finish()
           Toast.makeText(activity, question_1.getAnswer(),Toast.LENGTH_SHORT).show()
        }
    }
}