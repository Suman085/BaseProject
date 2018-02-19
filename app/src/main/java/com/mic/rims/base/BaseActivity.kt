package com.mic.rims.base

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.mic.rims.MyApplication
import com.mic.rims.R
import com.mic.rims.callbacks.BaseActivityCallback
import com.mic.rims.callbacks.PermissionCallback
import com.mic.rims.injection.component.ActivityComponent
import com.mic.rims.injection.component.DaggerActivityComponent
import com.mic.rims.injection.module.ActivityModule
import com.mic.rims.utils.Constants
import com.mic.rims.utils.LanguageHelper

/**
 * @author ishan
 * @since 08/07/16
 */

open class BaseActivity : AppCompatActivity(), BaseActivityCallback {
    /**
     * @return Returns toolbar linked with current activity
     */
    var toolbar: Toolbar? = null
        protected set

    private var activityComponent: ActivityComponent? = null
    private var permissionCallback: PermissionCallback?=null


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        toolbar = findViewById(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            setToolbarElevation()
        }
    }

    /**
     * Used for removing elevation from toolbar
     */
    fun hideToolbarElevation() {
        ViewCompat.setElevation(toolbar, 0f)
    }

    /**
     * Used for setting toolbar elevation
     */
    fun setToolbarElevation() {
        ViewCompat.setElevation(toolbar, 8f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * Used for dependency injection
     *
     * @return [ActivityComponent] which is used for injection
     */
    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(ActivityModule(this))
                    .applicationComponent(MyApplication.get(this).component())
                    .build()
        }
        return activityComponent!!
    }

    /**
     * This method is use to provide back button feature in the toolbar of activities
     */
    protected fun showBackButton() {
        if (supportActionBar != null) {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Displays a toast in current activity. The duration can of two types:
     *
     *  * SHORT
     *  * LONG
     *
     *
     * @param message   Message that the toast must show.
     * @param toastType Duration for which the toast must be visible.
     */
    @JvmOverloads
    fun showToast(message: String, toastType: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this@BaseActivity, message, toastType).show()
    }

    fun showSnackBar(view: View, message: String, snackbarType: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, message, snackbarType)
    }


    /**
     * Used for setting title of Toolbar
     *
     * @param title String you want to display as title
     */
    fun setActionBarTitle(title: String) {
        if (supportActionBar != null && getTitle() != null) {
            setTitle(title)
        }
    }

    protected fun setActionBarTitle(title: Int) {
        setActionBarTitle(resources.getString(title))
    }

    /**
     * Calls `setActionBarTitle()` to set Toolbar title
     *
     * @param title String you want to set as title
     */
    override fun setToolbarTitle(title: String) {
        setActionBarTitle(title)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageHelper.onAttach(base))
    }

    /**
     * Replace Fragment in FrameLayout Container.
     *
     * @param fragment       Fragment
     * @param addToBackStack Add to BackStack
     * @param containerId    Container Id
     */
    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, containerId: Int) {
        invalidateOptionsMenu()
        val backStateName = fragment.javaClass.name
        val fragmentPopped = supportFragmentManager.popBackStackImmediate(backStateName,
                0)

        if (!fragmentPopped && supportFragmentManager.findFragmentByTag(backStateName) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(containerId, fragment, backStateName)
            if (addToBackStack) {
                transaction.addToBackStack(backStateName)
            }
            transaction.commit()
        }
    }

    /**
     * It pops all the fragments which are currently in the backStack
     */
    fun clearFragmentBackStack() {
        val fm = supportFragmentManager
        val backStackCount = supportFragmentManager.backStackEntryCount
        for (i in 0 until backStackCount) {
            val backStackId = supportFragmentManager.getBackStackEntryAt(i).id
            fm.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun stackCount(): Int {
        return supportFragmentManager.backStackEntryCount
    }

    protected fun showDialog(message: String, title: String) {
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setCancelable(false)
        builder.show()
    }


    fun checkPermission(permission: String, permissionCallback: PermissionCallback){
        this.permissionCallback=permissionCallback
        if(isPermissionGranted(permission)){
            permissionCallback.onPermissionGranted()
        }else if(shouldShowPermissionRational(permission)){
            permissionCallback.onShouldShowPermissionRationale()
        }else {
            requestPermission(permission)
        }
    }

    private fun isPermissionGranted(permission: String) = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowPermissionRational(permission: String)=ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

    private fun requestPermission(permission: String) = ActivityCompat.requestPermissions(this, arrayOf(permission), Constants.REQUEST_PERMISSION_CODE)

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==Constants.REQUEST_PERMISSION_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                permissionCallback?.onPermissionGranted()
            }else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                permissionCallback?.onPermissionDenied()
            }
        }
    }

    protected fun showAlertForPermission(permission: String,rationaleMessage:String) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle("Alert")
        alertDialog.setMessage(rationaleMessage)

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW"
        ) { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",{dialog: DialogInterface?, which: Int ->
            dialog!!.dismiss()
            ActivityCompat.requestPermissions(this,
                    arrayOf(permission),
                    Constants.REQUEST_PERMISSION_CODE)

        })
        alertDialog.show()
    }


}
/**
 * Displays a toast in current activity. In this method the duration
 * supplied is Short by default. If you want to specify duration
 * use [BaseActivity.showToast] method.
 *
 * @param message Message that the toast must show.
 */
