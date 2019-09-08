package br.com.fiap.trabalhofinal.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.principal.PrincipalActivity
import br.com.fiap.trabalhofinal.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TIME_WAIT_SPLASHSCREEN = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val firstOpen = preferences.getBoolean("first_open", true)
        if (firstOpen) {
            animar()
            markAsAlreadyOpen(preferences)
        } else {
            goToLogin()
        }
    }

    private fun goToLogin() {
        val nextActivity = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextActivity)
        finish()
    }

    private fun animar() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        ivSplash.clearAnimation()
        ivSplash.startAnimation(anim)
        Handler().postDelayed({
            val nextActivity = Intent(this@SplashActivity, PrincipalActivity::class.java)
            startActivity(nextActivity)
            finish()
        }, TIME_WAIT_SPLASHSCREEN)
    }

    private fun markAsAlreadyOpen(preferences: SharedPreferences?) {
        val editor = preferences!!.edit()
        editor.putBoolean("first_open", false)
        editor.apply()
    }

}
