package com.maloac.pokedexproject.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private var _binding: ActivityAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.let { it.title = "About" }

    }
}