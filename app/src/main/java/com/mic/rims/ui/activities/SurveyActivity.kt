package com.mic.rims.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mic.rims.R
import com.mic.rims.base.BaseActivity

class SurveyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        showBackButton()
    }

}
