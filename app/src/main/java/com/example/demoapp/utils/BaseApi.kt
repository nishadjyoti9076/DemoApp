package com.example.demoapp.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class BaseApi {

    companion object {
        const val BASE_URL = "https://demo.otpl.in/"
        const val AES_KEY = "Y8ADeBXbGkhdUbjMvSk5GT"
        const val encryptedDeviceID = "70UZmzlaSjyk5yMnSZx5d7Zr+vqG4JGXRak2nJ6n190="
        // private const val encryptedRequestBody = "/wS8K4oMr/i/UY/+7DCzWiOh0SjZY4wuxS+PiVm6X0oqD7kgiURG+CZYt9DS5Qdbori0S2jji6+xwxGM88do+SGxneSsfxv6l4wAUMd5qw4="

        // Decrypt the device ID function
        fun decryptDeviceID(): String {
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            val keySpec = SecretKeySpec(AES_KEY.toByteArray(), "AES")
            cipher.init(Cipher.DECRYPT_MODE, keySpec)
            val decryptedBytes = cipher.doFinal(Base64.decode(encryptedDeviceID, Base64.DEFAULT))
            return String(decryptedBytes).trim()
        }
    }

}