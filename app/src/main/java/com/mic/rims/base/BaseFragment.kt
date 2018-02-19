package com.mic.rims.base

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.mic.rims.callbacks.BaseActivityCallback
import com.mic.rims.utils.Constants
import com.mic.rims.utils.LanguageHelper
import com.mic.rims.utils.ProgressBarHandler

abstract class BaseFragment : Fragment() {

    private var callback: BaseActivityCallback? = null
    private var progressBarHandler: ProgressBarHandler? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentView(), container, false)
    }

    abstract fun getContentView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        progressBarHandler = ProgressBarHandler(activity)
        super.onCreate(savedInstanceState)
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

    fun logError(throwable: Throwable) {
        Log.e("Error", throwable.message)
    }

    protected fun showAlertForPermission(permission: String,rationaleMessage:String) {
        val alertDialog = AlertDialog.Builder(activity!!).create()
        alertDialog.setTitle("Alert")
        alertDialog.setMessage(rationaleMessage)

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW"
        ) { dialog, which ->
            dialog.dismiss()
        }

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                object : DialogInterface.OnClickListener {

                    override fun onClick(dialog: DialogInterface, which: Int) {
                        dialog.dismiss()
                        ActivityCompat.requestPermissions(activity!!,
                                arrayOf(permission),
                                Constants.REQUEST_PERMISSION_CODE)
                    }
                })
        alertDialog.show()
    }

    protected fun showToast(message:String){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

}
