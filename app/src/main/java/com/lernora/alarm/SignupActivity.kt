package com.lernora.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        supportActionBar?.hide()
    }

    fun signupClicked(view: View) {
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Sign up", "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Sign up success.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Sign up", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Sign up failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun toLogin (view: View) {
        val randomIntent = Intent(this, MainActivity::class.java)
        startActivity(randomIntent)
    }
}
