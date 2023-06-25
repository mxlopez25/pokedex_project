package com.maloac.pokedexproject.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.databinding.ActivityDetailBinding
import com.maloac.pokedexproject.helpers.PokedexContants
import com.maloac.pokedexproject.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var url: String? = null
    private var index: String? = null
    private var name: String? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        url = intent.getStringExtra(PokedexContants.URL_TAG)
        index = intent.getStringExtra(PokedexContants.INDEX_TAG)
        name = intent.getStringExtra(PokedexContants.NAME_TAG)

        name?.let { viewModel.getPokemonByName(it) }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.pokedexDetail.collect {
                        data ->
                        Log.d("DataReceived", "$data")
                    }
                }

                launch {
                    viewModel.requestState.collect {
                        state ->
                        when(state) {
                            is MainViewModel.RequestState.InProcess -> {

                            }
                            is MainViewModel.RequestState.Success -> {

                            }
                            is MainViewModel.RequestState.Error -> {

                            } else -> Unit
                        }
                    }
                }
            }
        }

    }
}