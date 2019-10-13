package br.com.fiap.trabalhofinal.ui.about

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.helper.LocaleHelper
import kotlinx.android.synthetic.main.activity_about.*


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTitle(R.string.about)
        val pInfo = this.getPackageManager().getPackageInfo(packageName, 0)
        val version = pInfo.versionName
        versionNumber.text = version.toString()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

}
