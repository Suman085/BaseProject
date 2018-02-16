package com.mic.rims.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import com.mic.rims.callbacks.BaseActivityCallback
import com.mic.rims.utils.LanguageHelper
import com.mic.rims.utils.ProgressBarHandler

class BaseFragment : Fragment() {

    private var callback: BaseActivityCallback? = null
    private var progressBarHandler: ProgressBarHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBarHandler = ProgressBarHandler(activity)
    }

    /**
     * Used for setting title of Toolbar
     * @param title String you want to display as title
     */
    protected fun setToolbarTitle(title: String) {
        callback!!.setToolbarTitle(title)
    }

    /**
     * Displays [ProgressBarHandler]
     */
    protected fun showProgressBar() {
        progressBarHandler!!.show()
    }

    /**
     * Hides [ProgressBarHandler]
     */
    protected fun hideProgressBar() {
        progressBarHandler!!.hide()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(LanguageHelper.onAttach(context))
        val activity = context as? Activity
        try {
            callback = activity as BaseActivityCallback?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement BaseActivityCallback methods")
        }

    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

}
