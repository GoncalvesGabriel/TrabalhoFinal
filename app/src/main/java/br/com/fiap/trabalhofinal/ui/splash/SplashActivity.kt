package br.com.fiap.trabalhofinal.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.model.view.CheckStatusViewModel
import br.com.fiap.trabalhofinal.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    val checkStatusViewModel: CheckStatusViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animar()
        checkStatusViewModel.messageResponse.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
            if(checkStatusViewModel.success) {
                goToLogin()
            }
        })
        checkStatusViewModel.checkStatus()
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
    }

}
