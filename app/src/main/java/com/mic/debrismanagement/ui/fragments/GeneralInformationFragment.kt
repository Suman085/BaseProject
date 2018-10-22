package com.mic.debrismanagement.ui.fragments

import android.os.Bundle
import android.view.View
import com.mic.debrismanagement.R
import com.mic.debrismanagement.base.BaseFragment
import com.mic.debrismanagement.mvp.model.Survey
import com.mic.debrismanagement.navigateToFragment
import com.mic.debrismanagement.utils.Constants
import kotlinx.android.synthetic.main.general_information.*

/**
 * Created by Suman on 2/16/2018.
 */
class GeneralInformationFragment : BaseFragment() {
    var survey: Survey? = null

    companion object {
        fun newInstance(survey: Survey): GeneralInformationFragment {
            val bundle = Bundle()
            val generalInformationFragment = GeneralInformationFragment()
            bundle.putParcelable(Constants.SURVEY, survey)
            generalInformationFragment.arguments = bundle
            return generalInformationFragment
        }
    }

    override fun getContentView() = R.layout.general_information

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments?.getParcelable<Survey>(Constants.SURVEY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        btn_general_info_complete.setOnClickListener {
            activity!!.navigateToFragment(R.id.container,GeneralObservationFragment.newInstance(survey),GeneralObservationFragment::class.simpleName!!,true)
        }
    }

    private fun setUpViews() {

    }
}