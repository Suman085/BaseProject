package com.mic.rims.ui.fragments

import android.os.Bundle
import android.view.View
import com.mic.rims.R
import com.mic.rims.mvp.model.Survey
import com.mic.rims.utils.Constants

/**
 * Created by Suman on 2/17/2018.
 */
class ReconstructionStatusFragmentPending : ReconstructionStatusFragment() {
    var survey: Survey? = null

    companion object {
        fun newInstance(survey: Survey?): ReconstructionStatusFragmentPending {
            val bundle = Bundle()
            val fragment = ReconstructionStatusFragmentPending()
            bundle.putParcelable(Constants.SURVEY, survey)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments?.getParcelable<Survey>(Constants.SURVEY)
    }

    override fun getContentView() = R.layout.reconstruction_status_not_started

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

    }
}