package com.catnip.loginapp.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.catnip.loginapp.R
import com.catnip.loginapp.ui.homepage.HomepageActivity
import com.catnip.loginapp.ui.login.LoginActivity
import com.catnip.loginapp.utils.preference.UserPreference

class SplashScreenActivity : AppCompatActivity() {
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        setEventSplash()
    }

    override fun onDestroy() {
        super.onDestroy()
        //destroy the timer for case new activity still opened when splashscreen destoryed
        if(timer != null){
            timer?.cancel()
            timer = null
        }
    }


    private fun setEventSplash() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (UserPreference(this@SplashScreenActivity).isUserLoggedIn) {
                    // if user already login
                    val intent = Intent(this@SplashScreenActivity,HomepageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // if user not login
                    val intent = Intent(this@SplashScreenActivity,LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                finish()
            }
        }
        timer?.start()
    }

}