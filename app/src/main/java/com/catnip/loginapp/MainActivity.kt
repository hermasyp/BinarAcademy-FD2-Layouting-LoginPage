package com.catnip.loginapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catnip.loginapp.databinding.ActivityMainV2Binding
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainV2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainV2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            if(isFormLoginNotEmpty()){
                checkLogin()
            }
        }
        binding.llActionLoginFb.setOnClickListener {
            Toasty
                .info(this, getString(R.string.text_login_facebook_success), Toast.LENGTH_SHORT, true)
                .show()
        }
        binding.llActionLoginGmail.setOnClickListener {
            Toasty
                .info(this, getString(R.string.text_login_gmail_success), Toast.LENGTH_SHORT, true)
                .show()
        }
    }

    private fun isFormLoginNotEmpty(): Boolean {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        //for checking is email empty
        if (email.isEmpty()) {
            isFormValid = false
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.error = getString(R.string.error_email_empty)
        } else {
            binding.tilEmail.isErrorEnabled = false
        }

        //for checking is Password empty
        if (password.isEmpty()) {
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.error_password_empty)
        } else {
            binding.tilPassword.isErrorEnabled = false
        }
        return isFormValid
    }

    private fun checkLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (email == "admin@gmail.com" && password == "admin") {
            Toasty
                .success(this, getString(R.string.text_sucess_login_email), Toast.LENGTH_SHORT, true)
                .show()
        } else {
            Toasty
                .error(this, getString(R.string.error_wrong_email_and_password), Toast.LENGTH_SHORT, true)
                .show()
        }
    }

}