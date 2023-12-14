package com.example.demoapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.model.EncryptedRequestBody
import com.example.demoapp.repository.LoginRepository
import com.example.demoapp.utils.CheckNetwork
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context,private val repository: LoginRepository) : ViewModel() {

    val loginResultObserver= MutableLiveData<EncryptedRequestBody>()
    val errorHandler = MutableLiveData<String>()

    fun getLogin1(encryptedRequestBody: EncryptedRequestBody, encryptedDeviceID: String) {
        if (CheckNetwork.isNetworkAvailable(context)) {
            viewModelScope.launch {
                repository.doLogin1(context,encryptedRequestBody,encryptedDeviceID,loginResultObserver,errorHandler)
            }
        }else{
            errorHandler.postValue("Internet not available")
        }
    }

}