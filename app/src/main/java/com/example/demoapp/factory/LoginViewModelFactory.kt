package com.example.demoapp.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.repository.LoginRepository
import com.example.demoapp.viewmodel.LoginViewModel

class LoginViewModelFactory(private val context: Context, private val repository: LoginRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(context,repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}