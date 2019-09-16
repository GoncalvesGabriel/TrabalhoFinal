package br.com.fiap.trabalhofinal.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class LoginActivity : AppCompatActivity() {

    private val newUserRequestCode = 1

    private val mAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btLogin.setOnClickListener {
            hideKeyboard()
            if(validFields()) {
                mAuth.signInWithEmailAndPassword(
                    inputLoginEmail.text.toString(),
                    inputLoginPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        goToPrincipal()
                    } else {
                        Toast.makeText(this@LoginActivity, it.exception?.message,
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btSignup.setOnClickListener {
            startActivityForResult(Intent(this, SignUpActivity::class.java), newUserRequestCode)
        }

        inputLoginEmail.setOnFocusChangeListener(View.OnFocusChangeListener { view, hasFocus ->
            // to be implemented
        })
    }

    private fun goToPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                view.windowToken, HIDE_NOT_ALWAYS
            )
        }
    }

    private fun validFields(): Boolean {
        var valid = true
        if(inputLoginEmail.text.isNullOrBlank()) {
            textInputLoginEmail.error = resources.getString(R.string.error_required_field)
            valid = false
        }
        if(inputLoginPassword.text.isNullOrBlank()) {
            textInputLoginPassword.error = resources.getString(R.string.error_required_field)
            valid = false
        }
        return valid
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newUserRequestCode && resultCode == Activity.RESULT_OK) {
            inputLoginEmail.setText(data?.getStringExtra("email"))
        }
    }

}
