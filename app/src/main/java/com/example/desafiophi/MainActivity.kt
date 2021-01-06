package com.example.desafiophi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiophi.architecture.android.viewBinding
import com.example.desafiophi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}