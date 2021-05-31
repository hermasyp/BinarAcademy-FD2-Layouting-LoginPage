package com.catnip.loginapp.ui.homepage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catnip.loginapp.R
import com.catnip.loginapp.databinding.ActivityHomepageBinding
import com.catnip.loginapp.ui.login.LoginActivity
import com.catnip.loginapp.utils.browser.ProtectedMediaChromeClient
import com.catnip.loginapp.utils.preference.UserPreference
import es.dmoral.toasty.Toasty

class HomepageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        supportActionBar?.title = getString(R.string.title_activity_home)
        setContentView(binding.root)
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        binding.wvHomepage.settings.javaScriptEnabled = true
        binding.wvHomepage.settings.allowContentAccess = true
        binding.wvHomepage.settings.domStorageEnabled = true
        binding.wvHomepage.webChromeClient = ProtectedMediaChromeClient(this)
        binding.wvHomepage.loadUrl("https://open.spotify.com")
    }


    //to inflate layout menu to activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_homepage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //to add event click when menu clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                Toasty
                    .success(this,
                        getString(R.string.text_success_logout),Toast.LENGTH_SHORT, true)
                    .show()
                // delete user preference
                deleteLoginData()
                // navigate to login page
                navigateToLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun deleteLoginData() {
        UserPreference(this).isUserLoggedIn = false
    }
}