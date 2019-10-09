package br.com.fiap.trabalhofinal.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.trabalhofinal.R
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val pInfo = this.getPackageManager().getPackageInfo(packageName, 0)
        val version = pInfo.versionName
        versionNumber.text = version.toString()
    }
}
