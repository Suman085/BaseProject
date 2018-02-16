package com.mic.rims.callbacks

import android.view.View


interface OnClickItem {

    fun onItemClick(childView: View, position: Int)

    fun onItemLongPress(childView: View, position: Int)
}
