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

public class AESCipher implements Parcelable {
    private byte[] key;
    private byte[] iv;

    private static Cipher sAESCipher;
    public static final Cipher getCipher() {
        return sAESCipher;
    }

    public AESCipher() {
        // default constructor.
    }

    public AESCipher(byte[] key, byte[] iv) {
        this.key = key;
        this.iv = iv;
    }

    public void init() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(this.key);
        dest.writeByteArray(this.iv);
    }

    public void readFromParcel(Parcel in) {
        this.key = in.createByteArray();
        this.iv = in.createByteArray();
    }

    protected AESCipher(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<AESCipher> CREATOR = new Creator<AESCipher>() {
        @Override
        public AESCipher createFromParcel(Parcel source) {
            return new AESCipher(source);
        }

        @Override
        public AESCipher[] newArray(int size) {
            return new AESCipher[size];
        }
    };
}
