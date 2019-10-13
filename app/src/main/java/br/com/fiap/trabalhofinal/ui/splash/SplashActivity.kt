package br.com.fiap.trabalhofinal.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.helper.LocaleHelper
import br.com.fiap.trabalhofinal.model.view.CheckStatusViewModel
import br.com.fiap.trabalhofinal.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 3000L

    val checkStatusViewModel: CheckStatusViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        val isFirstOpen = preferences.getBoolean("open_first", true);
        if (isFirstOpen) {
            markAppRealdyOpen(preferences)
            animar()
        } else {
            startServerHeroku()
        }
    }

    private fun markAppRealdyOpen(preferences: SharedPreferences) {
        val editor = preferences.edit();
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun goToLogin() {
        val nextActivity = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextActivity)
        finish()
    }

    //Server Heroku sobe na primeira chamada.
    private fun startServerHeroku() {
        checkStatusViewModel.messageResponse.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
            if (checkStatusViewModel.success) {
                goToLogin()
            } else if(checkStatusViewModel.fail){
                startServerHeroku()
            }
        })
        checkStatusViewModel.checkStatus(this)
    }

    private fun animar() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        ivSplash.clearAnimation()
        ivSplash.startAnimation(anim)
        Handler().postDelayed({
            startServerHeroku()
        }, TEMPO_AGUARDO_SPLASHSCREEN)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

}
