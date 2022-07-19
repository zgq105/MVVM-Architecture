package com.guoqiang.base.utils

import com.tencent.mmkv.MMKV

/**
 * 封装MMKV统一调用，防止各业务模块key冲突问题
 */
class SpUtils private constructor() {

    companion object {

        private const val DEFAULT_MODULE_NAME = "sp_base"
        private var settingDefaultModuleName = ""

        fun initDefaultModuleName(name: String) {
            settingDefaultModuleName = name
        }

        /**
         * @param businessModule 业务模块名称
         * @param key
         * @param value
         */
        fun putValue(businessModule: String, key: String, value: Any?) {
            val mkv = MMKV.mmkvWithID(businessModule) ?: return
            when (value) {
                is String -> {
                    mkv.putString(key, value.toString())
                }
                is Boolean -> {
                    mkv.putBoolean(key, value)
                }
                is Int -> {
                    mkv.putInt(key, value)
                }
                is Float -> {
                    mkv.putFloat(key, value)
                }
                is Long -> {
                    mkv.putLong(key, value)
                }
                is ByteArray -> {
                    mkv.putBytes(key, value)
                }
                is Set<*> -> {
                    val set = hashSetOf<String>()
                    value.forEach {
                        if (it is String) {
                            set.add(it)
                        } else {
                            set.add(it?.toString() ?: "")
                        }
                    }
                    mkv.putStringSet(key, set)
                }
            }
        }

        fun putValue(key: String, value: Any?) {
            putValue(settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME }, key, value)
        }

        fun getString(businessModule: String, key: String, defValue: String?): String? {
            return MMKV.mmkvWithID(businessModule)?.getString(key, defValue)
        }

        fun getString(key: String, defValue: String?) {
            getString(settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME }, key, defValue)
        }

        fun getBoolean(businessModule: String, key: String, defValue: Boolean): Boolean {
            return MMKV.mmkvWithID(businessModule)?.getBoolean(key, defValue) ?: false
        }

        fun getBoolean(key: String, defValue: Boolean): Boolean {
            return getBoolean(
                settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME },
                key,
                defValue
            )
        }

        fun getInt(businessModule: String, key: String, defValue: Int): Int {
            return MMKV.mmkvWithID(businessModule)?.getInt(key, defValue) ?: -1
        }

        fun getInt(key: String, defValue: Int): Int {
            return getInt(settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME }, key, defValue)
        }

        fun getFloat(businessModule: String, key: String, defValue: Float): Float {
            return MMKV.mmkvWithID(businessModule)?.getFloat(key, defValue) ?: -1.0F
        }

        fun getFloat(key: String, defValue: Float): Float {
            return getFloat(settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME }, key, defValue)
        }

        fun getLong(businessModule: String, key: String, defValue: Long): Long {
            return MMKV.mmkvWithID(businessModule)?.getLong(key, defValue) ?: -1
        }

        fun getLong(key: String, defValue: Long): Long {
            return getLong(settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME }, key, defValue)
        }


        fun getBytes(businessModule: String, key: String, defValue: ByteArray): ByteArray {
            return MMKV.mmkvWithID(businessModule)?.getBytes(key, defValue) ?: ByteArray(0)
        }

        fun getBytes(key: String, defValue: ByteArray): ByteArray {
            return getBytes(settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME }, key, defValue)
        }


        fun getStringSet(businessModule: String, key: String, defValue: Set<String>): Set<String> {
            return MMKV.mmkvWithID(businessModule)?.getStringSet(key, defValue) ?: hashSetOf()
        }

        fun getStringSet(key: String, defValue: Set<String>): Set<String> {
            return getStringSet(
                settingDefaultModuleName.ifEmpty { DEFAULT_MODULE_NAME },
                key,
                defValue
            )
        }

    }
}