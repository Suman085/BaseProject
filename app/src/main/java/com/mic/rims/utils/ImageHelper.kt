package com.mic.rims.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Suman on 2/19/2018.
 */

class ImageHelper {
    companion object {
        fun getFileSize(path:String){
            try {
                val file = File(path)
                var length = file.length()
                length = length / 1024
                println("File Path : " + file.path + ", File size : " + length + " KB")
            } catch (e: Exception) {
                println("File not found : " + e.message + e)
            }
        }

        fun getObservableOfImage(photoPathToResize: String?): Observable<String?>? {

            return Observable.defer{->
                val b = BitmapFactory.decodeFile(photoPathToResize)
                val out = Bitmap.createScaledBitmap(b, b.width / 3, b.height / 3, false)
                val file = File(photoPathToResize)
                val fOut: FileOutputStream
                try {
                    fOut = FileOutputStream(file)
                    out.compress(Bitmap.CompressFormat.JPEG, 50, fOut)
                    fOut.flush()
                    fOut.close()
                    b.recycle()
                    out.recycle()
                } catch (e: Exception) {
                    Log.d("error", e.message)
                }
                return@defer Observable.just(photoPathToResize)
            }
        }
    }

}
