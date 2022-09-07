package com.guoqiang;

import android.util.Base64;

/**
 * author: zgq
 * date: 2022/8/4 5:43 下午
 * destcription:
 */
public class Base64Utils {
    private static final String TAG = "Base64Utils";
    private static final int FLAG_DEFAULT = 0;
    private static final int FLAG_URL = 10;
    private static final int FLAG_URL_NOPADDING = 11;

    public Base64Utils() {
    }

    public static byte[] decode(String var0) {
        byte[] var1 = new byte[0];
        if (var0 != null) {
            try {
                return Base64.decode(var0, 0);
            } catch (IllegalArgumentException var2) {
                return var1;
            }
        } else {
            return var1;
        }
    }

    public static byte[] decodeUrlSafe(String var0) {
        byte[] var1 = new byte[0];
        if (var0 != null) {
            try {
                return Base64.decode(var0, 10);
            } catch (IllegalArgumentException var2) {
                return var1;
            }
        } else {
            return var1;
        }
    }

    public static byte[] decodeUrlSafeNoPadding(String var0) {
        byte[] var1 = new byte[0];
        if (var0 != null) {
            try {
                return Base64.decode(var0, 11);
            } catch (IllegalArgumentException var2) {
                return var1;
            }
        } else {
            return var1;
        }
    }

    public static String encode(byte[] var0) {
        return var0 != null ? Base64.encodeToString(var0, 0) : null;
    }

    public static String encodeUrlSafe(byte[] var0) {
        return var0 != null ? Base64.encodeToString(var0, 10) : null;
    }

    public static String encodeUrlSafeNoPadding(byte[] var0) {
        return var0 != null ? Base64.encodeToString(var0, 11) : null;
    }

}
