package br.com.fiap.trabalhofinal.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TIME_WAIT_SPLASHSCREEN = 10000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animar()
    }

    private fun animar() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        ivSplash.clearAnimation()
        ivSplash.startAnimation(anim)
        Handler().postDelayed({
            val nextActivity = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(nextActivity)
            finish()
        }, TIME_WAIT_SPLASHSCREEN)
    }
}
