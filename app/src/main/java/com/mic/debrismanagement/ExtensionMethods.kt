package com.mic.debrismanagement

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Suman on 2/19/2018.
 */

fun FragmentActivity.navigateToFragment(container: Int, fragment: Fragment, backStackName: String, addToBackstack: Boolean = false) {
    if (addToBackstack) {
        supportFragmentManager.beginTransaction().replace(container, fragment).addToBackStack(backStackName).commit()
    } else {
        supportFragmentManager.beginTransaction().replace(container, fragment).commitNow()
    }
}

fun ImageView.loadImage(imagePath: String?) {
    if (imagePath!= null) {
        Glide.with(this)
                .load(imagePath)
                .apply(RequestOptions().centerCrop())
                .into(this)
    }
}

