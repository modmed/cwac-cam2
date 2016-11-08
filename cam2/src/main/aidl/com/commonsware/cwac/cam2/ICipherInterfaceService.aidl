// ICipherInterfaceService.aidl
package com.commonsware.cwac.cam2;

import com.commonsware.cwac.cam2.AESCipher;

interface ICipherInterfaceService {
	void putCipherParams(out AESCipher aesCipher);
}
