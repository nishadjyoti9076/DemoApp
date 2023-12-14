package com.example.demoapp.utils

import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.JsonObject
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object ApiJsonMap {


    /*
    {"Username":"SC0000001","Password":"Otpl@123","ReqDeviceID":"c7a9459a2dc0663e"}
    */
    @RequiresApi(Build.VERSION_CODES.O)
    fun decryptAES(encryptedText: String): String {
        val keyBytes: ByteArray = BaseApi.AES_KEY.toByteArray(charset("UTF-8"))
        val keySpec: Key = SecretKeySpec(keyBytes, "AES")

        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec)

        val encryptedBytes: ByteArray = java.util.Base64.getDecoder().decode(encryptedText)
        val decryptedBytes: ByteArray = cipher.doFinal(encryptedBytes)

        return String(decryptedBytes, charset("UTF-8"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encriptAES(encryptedText: String): String {
        val keyBytes: ByteArray = BaseApi.AES_KEY.toByteArray(charset("UTF-8"))
        val keySpec: Key = SecretKeySpec(keyBytes, "AES")

        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)

        val encryptedBytes: ByteArray = java.util.Base64.getDecoder().decode(encryptedText)
        return String(encryptedBytes, charset("UTF-8"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LoginCredentialjson(username: String, password: String, reqDeviceID: String): String {
        val jsonObject = JsonObject()
        try {
            jsonObject.addProperty("Username", username)
            jsonObject.addProperty("Password", password)
            jsonObject.addProperty("ReqDeviceID", reqDeviceID)

            val jsonString = jsonObject.toString()
            Log.e("JsonString","${jsonString}")
            return encriptAES(jsonString.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("JsonError","${e.message}")
            return ""
        }
    }

    fun LoginBodyjson(encryptedBody : String) : JsonObject{
        val jsonObject=JsonObject()
        try {
            jsonObject.addProperty("EncReq",encryptedBody)
        }catch (e : java.lang.Exception){
            e.message
            Log.e("JsonError","${e.message}")
        }
        return jsonObject
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeBase64(input: String): String {
        val encodedBytes = java.util.Base64.getEncoder().encode(input.toByteArray())
        return String(encodedBytes)
    }



}