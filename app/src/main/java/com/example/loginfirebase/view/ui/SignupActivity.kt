package com.example.loginfirebase.view.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfirebase.R
import com.example.loginfirebase.databinding.ActivitySignupBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignup.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()



            if (email.isEmpty() || password.isEmpty()) {
                val snackbar = Snackbar.make(it, "Fields need to be filled", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this@SignupActivity, "account created", Toast.LENGTH_LONG)
                            .show()
                        binding.etEmail.setText("")
                        binding.etPassword.setText("")
                    }


                }.addOnFailureListener {
                    val message = when(it) {
                        is FirebaseAuthWeakPasswordException -> "At least have a 6 digit password"
                        is FirebaseAuthInvalidCredentialsException -> "Put an valid email"
                        is FirebaseAuthUserCollisionException -> "Ur account has already been created"
                        is FirebaseNetworkException -> "We did not detect internet access"
                        else -> "error please try again"
                    }
                    Toast.makeText(this@SignupActivity, "${message}", Toast.LENGTH_LONG).show()

                }
            }
        }

    }
}