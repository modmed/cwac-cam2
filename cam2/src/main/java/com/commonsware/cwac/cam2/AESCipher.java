package com.commonsware.cwac.cam2;

import android.os.Parcel;
import android.os.Parcelable;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by parag.goel on 11/4/16.
 */

public class AESCipher {
    private static Cipher sAESCipher;
    public static Cipher getCipher() {
        return sAESCipher;
    }

    private AESCipher() {}

    public static synchronized void init(byte[] key, byte[] iv) {
        try {
            sAESCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            // Initialize Cipher to encrypt.
            sAESCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
