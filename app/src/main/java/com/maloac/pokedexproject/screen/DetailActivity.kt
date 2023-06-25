package com.maloac.pokedexproject.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var url: String? = null
    private var index: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        url = intent.getStringExtra("URL")
        index = intent.getStringExtra("INDEX")



    }
}