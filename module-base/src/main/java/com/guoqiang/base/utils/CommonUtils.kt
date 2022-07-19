package com.guoqiang.base.utils

import android.annotation.SuppressLint
import android.content.Context
import kotlin.Throws
import android.content.res.AssetManager
import android.provider.Settings
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by Waheed on 04,November,2019
 */
object CommonUtils {
    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String?): String {
        val manager = context.assets
        val inputStream = manager.open(jsonFileName!!)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.forName("UTF-8"))
    }
}