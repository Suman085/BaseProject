package com.mic.debrismanagement.ui.activities

import android.Manifest
import android.os.Bundle
import com.mic.debrismanagement.R
import com.mic.debrismanagement.base.BaseActivity
import com.mic.debrismanagement.callbacks.PermissionCallback
import com.mic.debrismanagement.mvp.model.Survey
import com.mic.debrismanagement.navigateToFragment
import com.mic.debrismanagement.ui.fragments.GeneralInformationFragment

class SurveyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        setTitle("Survey")
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,object :PermissionCallback{
            override fun onPermissionGranted() {
//                showToast("PermissionGranted")
            }

            override fun onShouldShowPermissionRationale() {
                showAlertForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,"We need external storage permission to store images")
            }

            override fun onPermissionDenied() {
//                showToast("PermissionDenied")
            }

        })
        showBackButton()
        navigateToFragment(R.id.container,GeneralInformationFragment.newInstance(Survey()),GeneralInformationFragment::class.simpleName!!)


    }



}
