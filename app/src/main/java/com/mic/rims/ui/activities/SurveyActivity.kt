package com.mic.rims.ui.activities

import android.Manifest
import android.os.Bundle
import com.mic.rims.R
import com.mic.rims.base.BaseActivity
import com.mic.rims.callbacks.PermissionCallback
import com.mic.rims.mvp.model.Survey
import com.mic.rims.navigateToFragment
import com.mic.rims.ui.fragments.GeneralInformationFragment

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
