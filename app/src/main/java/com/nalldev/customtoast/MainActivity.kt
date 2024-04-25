package com.nalldev.customtoast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

    lateinit var successToast : NalToast
    lateinit var errorToast : NalToast
    lateinit var infoToast : NalToast

    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        successToast = NalToast(binding.root, Type.SUCCESS)

        errorToast = NalToast(binding.root, Type.FAIL)

        infoToast = NalToast(binding.root, Type.INFO)

        binding.tvHello.setOnClickListener {

            if (index == 1) {
                successToast.show("SHORT!!", Duration.SHORT, lifecycleScope)
                index++
            } else if (index == 2) {
                errorToast.show("LONG!!", Duration.LONG, lifecycleScope)
                index++
            } else if (index == 3) {
                infoToast.show("SHORT!!", Duration.SHORT, lifecycleScope)
                index++
            } else {
                successToast.show("LONG!!", Duration.SHORT, lifecycleScope)
                index = 1
            }
        }
    }
}