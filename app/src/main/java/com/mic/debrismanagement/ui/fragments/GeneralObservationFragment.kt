package com.mic.debrismanagement.ui.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import com.mic.debrismanagement.BuildConfig
import com.mic.debrismanagement.R
import com.mic.debrismanagement.base.BaseActivity
import com.mic.debrismanagement.base.BaseFragment
import com.mic.debrismanagement.callbacks.PermissionCallback
import com.mic.debrismanagement.loadImage
import com.mic.debrismanagement.mvp.model.Survey
import com.mic.debrismanagement.navigateToFragment
import com.mic.debrismanagement.utils.Constants
import com.mic.debrismanagement.utils.ImageHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.general_observation.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Suman on 2/16/2018.
 */
class GeneralObservationFragment : BaseFragment() {
    var survey: Survey? = null
    private var imagePath: String? = null
    private var identifyImage: Int? = 0
    private val disposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(survey: Survey?): GeneralObservationFragment {
            val bundle = Bundle()
            val generalObservationFragment = GeneralObservationFragment()
            bundle.putParcelable(Constants.SURVEY, survey)
            generalObservationFragment.arguments = bundle
            return generalObservationFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        survey = arguments?.getParcelable<Survey>(Constants.SURVEY)
    }

    override fun getContentView() = R.layout.general_observation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        btn_next.setOnClickListener {
            navigateToRespectiveFragment();
        }
        ll_beneficiary_image.setOnClickListener {
            identifyImage = 1
            checkPermissionAndOpenCamera()
        }
        ll_existing_house_image.setOnClickListener {
            identifyImage = 2
            checkPermissionAndOpenCamera()
        }
        ll_nissa_card.setOnClickListener {
            identifyImage = 3
            checkPermissionAndOpenCamera()
        }
        ll_red_card.setOnClickListener {
            identifyImage = 4
            checkPermissionAndOpenCamera()
        }
    }


    private fun navigateToRespectiveFragment() {
        if (qv_construction_status.getAnswer()==resources.getStringArray(R.array.construction_status)[0]) {
            activity!!.navigateToFragment(R.id.container, ReconstructionStatusFragmentStarted.newInstance(survey), ReconstructionStatusFragmentStarted::class.simpleName!!, true)
        } else {
            activity!!.navigateToFragment(R.id.container, ReconstructionStatusFragmentPending.newInstance(survey), ReconstructionStatusFragmentPending::class.simpleName!!, true)
        }
    }


    private fun setUpViews() {
        iv_beneficiary_image.loadImage(survey?.beneficiaryImage)
        iv_house_image.loadImage(survey?.houseImage)
        iv_nissa_card.loadImage(survey?.nissaImage)
        iv_red_card.loadImage(survey?.redCardImage)
    }

    private fun createImageFile(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image: File? = null
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir)
            imagePath = image.absolutePath

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return image
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity!!.getPackageManager()) != null) {
            val photoFile: File?
            try {
                photoFile = createImageFile()
                if (photoFile != null) {
                    val photoURI = FileProvider.getUriForFile(activity!!, BuildConfig.APPLICATION_ID + ".provider", photoFile)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, Constants.REQUEST_IMAGE_CAPTURE)
                }
            } catch (ex: Exception) {
                Log.e("Error", "exception while creating image file:" + ex.toString())
            }
        }
    }

    private fun checkPermissionAndOpenCamera() {
        (activity!! as BaseActivity).checkPermission(Manifest.permission.CAMERA, object : PermissionCallback {
            override fun onPermissionGranted() {
                openCamera()
            }

            override fun onShouldShowPermissionRationale() {
                showAlertForPermission(Manifest.permission.CAMERA, "We need camera permission to take pictures.")
            }

            override fun onPermissionDenied() {

            }
        })

    }


    private fun setPic() {
        if (imagePath != null) {
            disposable.add(ImageHelper.getObservableOfImage(imagePath)!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        when (identifyImage) {

                            1 -> {
                                iv_beneficiary_image.loadImage(it)
                                survey!!.beneficiaryImage = it
                            }
                            2 -> {
                                iv_house_image.loadImage(it)
                                survey!!.houseImage = it
                            }
                            3 -> {
                                iv_nissa_card.loadImage(it)
                                survey!!.nissaImage = it
                            }
                            4 -> {
                                iv_red_card.loadImage(it)
                                survey!!.redCardImage = it
                            }
                        }
                    }, { throwable: Throwable ->
                        Log.e("ThrowableImage", throwable.message)
                        logError(throwable)
                    }))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("image", requestCode.toString())
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_IMAGE_CAPTURE) {
            setPic()
        }
    }

}