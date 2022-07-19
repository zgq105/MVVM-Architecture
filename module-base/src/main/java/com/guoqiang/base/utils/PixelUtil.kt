package com.guoqiang.base.utils

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

/**
 * Created by Waheed on 04,November,2019
 */

object PixelUtil {

    fun dpToPx(context: Context, dp: Int): Int {
        return (dp * getPixelScaleFactor(context)).roundToInt()
    }

    fun pxToDp(context: Context, px: Int): Int {
        return (px / getPixelScaleFactor(context)).roundToInt()
    }

    private fun getPixelScaleFactor(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT
    }
}