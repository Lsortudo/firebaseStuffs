package com.example.loginfirebase.view.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfirebase.R
import com.example.loginfirebase.databinding.ActivitySigninBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SigninActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        val auth = FirebaseAuth.getInstance()

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()


            if (email.isEmpty() || password.isEmpty()) {

                val snackbar = Snackbar.make(it, "Fields need to be filled", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()

            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            intentToDatabase()
                        }
                    }
                    .addOnFailureListener {
                        /*val message = when(it) {
                            is FirebaseAuthWeakPasswordException -> "At least have a 6 digit password"
                            is FirebaseAuthInvalidCredentialsException -> "Put an valid email"
                            is FirebaseAuthUserCollisionException -> "Ur account has already been created"
                            is FirebaseNetworkException -> "We did not detect internet access"
                            else -> "error please try again"
                        }*/
                        Toast.makeText(this@SigninActivity, "${it}", Toast.LENGTH_LONG).show()
                    }
            }
        }

        binding.tvDontHaveAccount.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun intentToDatabase() {
        val intent = Intent(this, DatabaseActivity::class.java)
        startActivity(intent)
        finish()
    }
}