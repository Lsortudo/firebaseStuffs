package com.example.loginfirebase.view.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginfirebase.R
import com.example.loginfirebase.databinding.ActivityFirestoreBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirestoreBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSubmit.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val user = binding.etUser.text.toString()
            val date = binding.etDate.text.toString()
            val age = binding.etAge.text.toString()

            if (title.isEmpty() || description.isEmpty() || user.isEmpty() || date.isEmpty() || age.isEmpty()) {

                val snackbar = Snackbar.make(it, "Fields need to be filled", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()

            } else {
                postData(title, description, user, date, age)

            }
        }

    }

    private fun postData(

        title: String,
        description: String,
        user: String,
        date: String,
        age: String,

        ) {

        val map = hashMapOf(

            "title" to title,
            "description" to description,
            "user" to user,
            "date" to date,
            "age" to age,

            )

        db.collection("news").document().set(map).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    this@FirestoreActivity,
                    "data sent to the database",
                    Toast.LENGTH_LONG
                ).show()
                clearFields()
            }
        }.addOnFailureListener {
            /*val message = when (it) {
                is FirebaseAuthWeakPasswordException -> "At least have a 6 digit password"
                is FirebaseAuthInvalidCredentialsException -> "Put an valid email"
                is FirebaseAuthUserCollisionException -> "Ur account has already been created"
                is FirebaseNetworkException -> "We did not detect internet access"
                else -> "error please try again"
            }*/
            Toast.makeText(this@FirestoreActivity, "teste", Toast.LENGTH_LONG).show()
        }
    }
    private fun clearFields() {
        binding.etTitle.setText("")
        binding.etDescription.setText("")
        binding.etUser.setText("")
        binding.etDate.setText("")
        binding.etAge.setText("")
    }
}