package com.example.demoapp.api

import com.example.demoapp.model.EncryptedRequestBody
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {

    @Headers("Content-Type: application/json")
    @POST("nbdiup/Areas/SmartClass/Resource/SCLogin")
    fun login(@Header("ReqDeviceID") encryptedDeviceID: String?,
              @Body jsonBody: JsonObject) : Call<EncryptedRequestBody>

    @Headers("Content-Type: application/json")
    @POST("/nbdiup/Areas/SmartClass/Resource/SCLogin")
    fun login(
        @Body request: EncryptedRequestBody,
        @Header("ReqDeviceID") encryptedDeviceID: String
    ): Call<EncryptedRequestBody> // Replace YourResponseType with the expected response type



}