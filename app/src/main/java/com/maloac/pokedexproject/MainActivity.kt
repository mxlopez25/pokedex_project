package com.maloac.pokedexproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maloac.pokedexproject.adapters.PokedexListAdapter
import com.maloac.pokedexproject.databinding.ActivityMainBinding
import com.maloac.pokedexproject.models.ResultsItem
import com.maloac.pokedexproject.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    var adapter: PokedexListAdapter? = null

    lateinit var rvPokedex: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = findViewById(R.id.progressBar)
        rvPokedex = findViewById(R.id.rvPokedex)
        rvPokedex.layoutManager = LinearLayoutManager(this)
        adapter = PokedexListAdapter(listOf(ResultsItem(name = "Test", url = "Test")))
        rvPokedex.adapter = adapter

        viewModel.getAllPokemons()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.pokemonList.collect {
                            data ->
                        adapter = data.results?.let { it -> PokedexListAdapter(it) }
                        rvPokedex.adapter = adapter
                    }
                }

                launch {
                    viewModel.requestState.collect {
                        state ->
                        when (state) {
                            is MainViewModel.RequestState.InProcess -> {
                                rvPokedex.isVisible = false
                                progressBar.isVisible = true
                            }
                            is MainViewModel.RequestState.Success -> {
                                rvPokedex.isVisible = true
                                progressBar.isVisible = false
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