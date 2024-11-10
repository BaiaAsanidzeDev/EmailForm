package com.example.emailform

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emailform.databinding.ActivityResponseBinding

class ResponseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResponseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val arguments =
            requireNotNull(intent?.extras) { "There should be parameters or your more meaningful message." }
        with(arguments) {
            binding.yourEmail.text = "From: ${getString("you")}"
            binding.otherEmail.text = "To: ${getString("other")}"
            binding.message.text = "Message: ${getString("message")}"
        }

        binding.clear.setOnClickListener {
            if (binding.yourEmail.text.isNotEmpty()) {
                binding.yourEmail.text = ""
                binding.otherEmail.text = ""
                binding.message.text = ""
                Toast.makeText(
                    this, "Cleared successfully", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}