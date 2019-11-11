package com.lernora.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        supportActionBar?.hide()
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(baseContext, "Already Logged in.",
                Toast.LENGTH_SHORT).show()

            val navIntent = Intent(this, BottomActivity::class.java)
            startActivity(navIntent)
        }

    }

    fun loginClicked (view: View) {
        val pgsBar = findViewById<ProgressBar>(R.id.progressBar)
        pgsBar.visibility = View.VISIBLE

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)


        auth.signInWithEmailAndPassword("a@a.com", "123123")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Login success.",
                        Toast.LENGTH_SHORT).show()

                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    pgsBar.visibility = View.GONE
                    window.attributes.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL

                    val navIntent = Intent(this, BottomActivity::class.java)
                    startActivity(navIntent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    pgsBar.visibility = View.INVISIBLE
                    window.attributes.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL
                }

                // ...
            }
    }

    fun toSignup (view: View) {
        val signupIntent = Intent(this, SignupActivity::class.java)
        startActivity(signupIntent)
    }
}
