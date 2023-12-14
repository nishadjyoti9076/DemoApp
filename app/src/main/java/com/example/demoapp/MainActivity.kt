package com.example.demoapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.factory.LoginViewModelFactory
import com.example.demoapp.model.EncryptedRequestBody
import com.example.demoapp.model.LoginRequest
import com.example.demoapp.repository.LoginRepository
import com.example.demoapp.utils.ApiJsonMap
import com.example.demoapp.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: LoginViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository=LoginRepository()
        val mLoginfactory=LoginViewModelFactory(this,repository)
        viewModel=ViewModelProvider(this,mLoginfactory)[LoginViewModel::class.java]


        binding.btnSignin.setOnClickListener {
            if (validation()){
                checkLoginCredential()
            }
        }
        LoginResponse(viewModel)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun checkLoginCredential(){

        val loginRequest = LoginRequest("SC0000001", "Otpl@123", "c7a9459a2dc0663e")
        val encryptedRequestBody = EncryptedRequestBody(ApiJsonMap.encodeBase64(loginRequest.toString()))
        val encryptedDeviceID = "70UZmzlaSjyk5yMnSZx5d7Zr+vqG4JGXRak2nJ6n190="

        viewModel.getLogin1(
            encryptedRequestBody,encryptedDeviceID
        )
    }

    fun LoginResponse(loginViewModel: LoginViewModel){
        loginViewModel.loginResultObserver.observe(this){
            it?.let {
                Log.e("Response>>>>","${it.toString()}")
                binding.txtResponseData.text= "${it.encReq.toString()}"
                Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
            }
        }

        loginViewModel.errorHandler.observe(this){
            it?.let {
                Toast.makeText(this,"${it.toString()}",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validation() : Boolean{
        if (binding.etEmail.text.isNullOrEmpty()){
            Toast.makeText(this,"Enter userid",Toast.LENGTH_LONG).show()
            return false
        }else if (binding.etEmail.text.isNullOrEmpty()){
            Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show()
            return false
        }else{
            return true
        }
    }
}