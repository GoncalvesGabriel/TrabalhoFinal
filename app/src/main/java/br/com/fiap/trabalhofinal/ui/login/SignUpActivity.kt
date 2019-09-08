package br.com.fiap.trabalhofinal.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.trabalhofinal.R
import br.com.fiap.trabalhofinal.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.ext.android.inject

class SignUpActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btCriar.setOnClickListener{
            mAuth.createUserWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener{
                if (it.isSuccessful) {
                    saveInRealTimeDatabase()
                }
            }
        }
    }

    private fun saveInRealTimeDatabase() {
        val user = User(inputNome.text.toString(), inputEmail.text.toString())
        FirebaseDatabase.getInstance().getReference("User")
            .child(mAuth.currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_SHORT).show()
                    val returnIntent = Intent()
                    returnIntent.putExtra("email", inputEmail.text.toString())
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao criar usuário: " + it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }

    }


}
