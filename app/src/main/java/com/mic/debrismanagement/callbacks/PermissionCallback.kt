package com.mic.debrismanagement.callbacks

/**
 * Created by Suman on 2/19/2018.
 */
interface PermissionCallback {
    fun onPermissionGranted()
    fun onShouldShowPermissionRationale()
    fun onPermissionDenied()

}