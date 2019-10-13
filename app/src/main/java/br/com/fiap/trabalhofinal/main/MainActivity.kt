package br.com.fiap.trabalhofinal.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.helper.LocaleHelper
import br.com.fiap.trabalhofinal.ui.about.AboutActivity
import br.com.fiap.trabalhofinal.ui.list.ProductListActivity
import br.com.fiap.trabalhofinal.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by inject()

    lateinit var language : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.title_main_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        language = getString(R.string.avaliable_language)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val drawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                setTitle(R.string.app_name)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setTitle(R.string.menuTitle)
            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener { item ->
            when (item?.itemId) {

                R.id.action_produtos -> {
                    goToNextActivity(ProductListActivity::class.java)
                }
                R.id.action_logout -> {
                    mAuth.signOut()
                    goToNextActivity(LoginActivity::class.java)
                    finish()
                }

                R.id.action_about -> {
                    goToNextActivity(AboutActivity::class.java)
                }

                R.id.action_exit -> {
                    finish()
                }

                R.id.change_language -> {
                    LocaleHelper.setLocale(this@MainActivity, language)
                    startActivity(this@MainActivity.intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        return true
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    private fun goToNextActivity(activity: Class<out AppCompatActivity>) {
        val intent = Intent(this, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}


