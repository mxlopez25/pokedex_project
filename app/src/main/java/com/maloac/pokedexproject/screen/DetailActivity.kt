package com.maloac.pokedexproject.screen

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.adapters.MovesListAdapter
import com.maloac.pokedexproject.databinding.ActivityDetailBinding
import com.maloac.pokedexproject.helpers.PokedexContants
import com.maloac.pokedexproject.models.PokedexData
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

        binding.rvMoves.layoutManager = LinearLayoutManager(this)
        binding.rvMoves.adapter = MovesListAdapter(listOf(PokedexData.Moves(PokedexData.Move(name = "Loading", url = "Loading"))))


        name?.let { viewModel.getPokemonByName(it) }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.pokedexDetail.collect {
                        data ->
                        data.moves?.let { binding.rvMoves.adapter = MovesListAdapter(it) }
                        data.name?.let { binding.tvPokemonName.text = it }
                        data.types?.let {
                            if (it.size > 1) {
                                binding.btnTypeOne.text = it[0].type?.name.toString()
                                binding.btnTypeTwo.text = it[1].type?.name.toString()
                            } else {
                                binding.btnTypeOne.text = it[0].type?.name.toString()
                            }
                        }

                    }
                }

                launch {
                    viewModel.requestState.collect {
                        state ->
                        when(state) {
                            is MainViewModel.RequestState.InProcess -> {
                                binding.tvDetailsError.isVisible = false
                                binding.clPokemonDetails.isVisible = false
                                binding.detailsProgressBar.isVisible = true
                            }
                            is MainViewModel.RequestState.Success -> {
                                binding.tvDetailsError.isVisible = false
                                binding.clPokemonDetails.isVisible = true
                                binding.detailsProgressBar.isVisible = false
                            }
                            is MainViewModel.RequestState.Error -> {
                                binding.tvDetailsError.isVisible = true
                                binding.clPokemonDetails.isVisible = false
                                binding.detailsProgressBar.isVisible = false
                            } else -> Unit
                        }
                    }
                }
            }
        }

    }
}