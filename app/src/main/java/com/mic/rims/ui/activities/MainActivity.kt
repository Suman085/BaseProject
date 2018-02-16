package com.mic.rims.ui.activities

import android.os.Bundle
import com.mic.rims.base.BaseActivity
import com.mic.rims.utils.ProgressBarHandler
import com.mic.rims.R
import com.mic.rims.persistence.PreferencesHelper
import javax.inject.Inject


class MainActivity : BaseActivity() {
    @Inject
    lateinit var preferenceHelper: PreferencesHelper
    private var progressBarHandler: ProgressBarHandler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this)
        progressBarHandler= ProgressBarHandler(this)

    }

    override fun onResume() {
        super.onResume()

    }

}
