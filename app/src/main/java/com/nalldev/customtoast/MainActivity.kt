package com.nalldev.customtoast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.nalldev.customtoast.databinding.ActivityMainBinding
import com.nalldev.naltoast.util.Duration
import com.nalldev.naltoast.util.Type
import com.nalldev.naltoast.view.NalToast

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var successToast : NalToast
    private lateinit var errorToast : NalToast
    private lateinit var infoToast : NalToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        successToast = NalToast(binding.root, Type.SUCCESS)

        errorToast = NalToast(binding.root, Type.FAIL)

        infoToast = NalToast(binding.root, Type.INFO)

        binding.apply {
            btnSuccess.setOnClickListener {
                successToast.show("You are great!", Duration.SHORT, lifecycleScope)
            }

            btnFail.setOnClickListener {
                errorToast.show("Sorry, wrong way", Duration.SHORT)
            }

            btnInfo.setOnClickListener {
                infoToast.show("Good morning", Duration.LONG)
            }
        }
    }
}