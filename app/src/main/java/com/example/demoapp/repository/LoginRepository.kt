package com.example.demoapp.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.api.APIClient
import com.example.demoapp.api.APIService
import com.example.demoapp.model.EncryptedRequestBody
import com.example.demoapp.utils.ApiJsonMap
import com.example.demoapp.utils.BaseApi
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginRepository {
    val client = APIClient.getRetrofitClient()
    fun doLogin1(context: Context, encryptedRequestBody: EncryptedRequestBody,
                 encryptedDeviceID: String,
                 loginResultObserver: MutableLiveData<EncryptedRequestBody>, errorHandler: MutableLiveData<String>) {
        val call: Call<EncryptedRequestBody> = client!!.create(APIService::class.java).login(
            encryptedRequestBody, encryptedDeviceID
        )
        call.enqueue(object : Callback<EncryptedRequestBody> {
            override fun onResponse(
                call: Call<EncryptedRequestBody>,
                response: Response<EncryptedRequestBody>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    val encryptedRequestBody = response.body()
                    Log.e("Response", "${encryptedRequestBody!!.encReq}")

                    encryptedRequestBody!!.encReq?.let {
                      //  val decriptedResponse = ApiJsonMap.decryptAES(encryptedRequestBody!!.encReq!!)
                      //  Log.e("decriptedResponse", "$decriptedResponse")
                        loginResultObserver.postValue(encryptedRequestBody)
                    }

                } else {
                    errorHandler.value = response.code().toString()
                    Log.e("ResponseErrorCode", "${response.code()} ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<EncryptedRequestBody>, t: Throwable) {
                errorHandler.value = t.message
                Log.e("ResponseErrorCode", "${t.message}")
            }
        })

    }

}