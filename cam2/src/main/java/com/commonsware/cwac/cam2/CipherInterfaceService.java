package com.commonsware.cwac.cam2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by parag.goel on 11/2/16.
 */

public class CipherInterfaceService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return mBinder;
    }

    private final ICipherInterfaceService.Stub mBinder = new ICipherInterfaceService.Stub() {
//        public int getPid(){
//            return Process.myPid();
//        }

        @Override
        public void putCipherParams(AESCipher aesCipher) {
            aesCipher.init();
        }
    };
}
