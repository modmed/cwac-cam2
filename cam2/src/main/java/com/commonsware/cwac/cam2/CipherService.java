package com.commonsware.cwac.cam2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import com.commonsware.cwac.cam2.AESCipher;

/**
 * Created by parag.goel on 1/3/17.
 */

public class CipherService extends Service {
    public static final int MSG_INIT_CIPHER = 1;

    public static final String KEY_ARRAY = "key_array";
    public static final String IV_ARRAY = "iv_array";

    /**
     * Handler of incoming messages from clients.
     */
    private static class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT_CIPHER:
                    Bundle bundle = msg.getData();
                    AESCipher.init(bundle.getByteArray(KEY_ARRAY), bundle.getByteArray(IV_ARRAY));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
