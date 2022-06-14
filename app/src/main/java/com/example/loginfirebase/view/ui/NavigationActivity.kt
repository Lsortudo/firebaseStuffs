package com.example.loginfirebase.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.example.loginfirebase.R
import com.example.loginfirebase.databinding.ActivityDatabaseBinding
import com.google.firebase.auth.FirebaseAuth

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btOffline.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*binding.tvTitle = coloredText(
            baseText = getString(R.string.my_title),
            coloredText = getString(R.string.my_title_colored_part),
            targetColor = ContextCompat.getColor(requireContext(), R.color.blue))*/



    }


    private fun coloredText (baseText: String, coloredText: String, targetColor: Int): SpannableStringBuilder {
        val transformText = "$baseText $coloredText"
        return SpannableStringBuilder(transformText).apply {
            setSpan(
                ForegroundColorSpan(targetColor),
                transformText.indexOf(coloredText),
                (transformText.indexOf(coloredText) + coloredText.length),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }



}
