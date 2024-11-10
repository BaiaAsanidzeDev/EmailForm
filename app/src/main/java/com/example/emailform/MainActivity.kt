package com.example.emailform

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emailform.databinding.ActivityMainBinding
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listeners()

    }

    private fun listeners() {
        binding.send.setOnClickListener {
            if (isEmpty()) {
                Toast.makeText(
                    this, "All field are required",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!isValidEmail(binding.yourEmail.text.toString())) {
                Toast.makeText(
                    this, "Enter Valid Email",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                navigate()
            }
        }
    }

    private fun navigate() {
        val intent = Intent(this, ResponseActivity::class.java)
        intent.putExtra("you", binding.yourEmail.text.toString())
        intent.putExtra("other", binding.otherEmail.text.toString())
        intent.putExtra("message", binding.message.text.toString())
        startActivity(intent)
    }


    private fun isEmpty(): Boolean = with(binding) {
        return@with yourEmail.text.isEmpty() || otherEmail.text.isEmpty() || message.text.isEmpty()
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}