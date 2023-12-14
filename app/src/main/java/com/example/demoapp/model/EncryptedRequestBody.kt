package com.example.demoapp.model

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    val Username: String,
    val Password: String,
    val ReqDeviceID: String
)

data class EncryptedRequestBody(
    @SerializedName("EncReq")
     var encReq: String? = null
)