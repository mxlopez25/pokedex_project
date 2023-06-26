package com.maloac.pokedexproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.maloac.pokedexproject.adapters.PokedexListAdapter
import com.maloac.pokedexproject.databinding.ActivityMainBinding
import com.maloac.pokedexproject.helpers.MainActivityPrefs
import com.maloac.pokedexproject.models.PokedexDataResponse
import com.maloac.pokedexproject.models.ResultsItem
import com.maloac.pokedexproject.screen.AboutActivity
import com.maloac.pokedexproject.viewmodel.MainViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch

val prefs: MainActivityPrefs by lazy {
    MainActivity.prefs!!
}

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    var adapter: PokedexListAdapter? = null

    var jsonData: PokedexDataResponse? = null

    companion object {
        var prefs: MainActivityPrefs? = null
        lateinit var instance: MainActivity
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.let { it.title = "Pokedex" }
        instance = this
        prefs = MainActivityPrefs(applicationContext)

        binding.rvPokedex.layoutManager = LinearLayoutManager(this)
        adapter = PokedexListAdapter(listOf(ResultsItem(name = "No Data", url = "Test")))
        binding.rvPokedex.adapter = adapter

        viewModel.getAllPokemons()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.pokemonList.collect { data ->
                        if (data.count == null && prefs!!.pokemonList != null) {
                            val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                            val jsonAdapter: JsonAdapter<PokedexDataResponse> = moshi.adapter(PokedexDataResponse::class.java)
                            prefs!!.pokemonList?.let { jsonData = jsonAdapter.fromJson(it) }
                            jsonData?.let {
                                adapter = PokedexListAdapter(it.results!!)
                                binding.rvPokedex.adapter = adapter
                            }

                        } else if (data.results != null) {

                            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                            val jsonAdapter = moshi.adapter(PokedexDataResponse::class.java)

                            prefs?.let { it.pokemonList = jsonAdapter.toJson(data) }

                            adapter = PokedexListAdapter(data.results)
                            binding.rvPokedex.adapter = adapter
                        } else {
                            adapter = PokedexListAdapter(listOf(ResultsItem(name = "No Data Available")))
                            binding.rvPokedex.adapter = adapter
                        }
                    }
                }

                launch {
                    viewModel.requestState.collect { state ->
                        when (state) {
                            is MainViewModel.RequestState.InProcess -> {
                                binding.rvPokedex.isVisible = false
                                binding.progressBar.isVisible = true
                            }
                            is MainViewModel.RequestState.Success -> {
                                binding.rvPokedex.isVisible = true
                                binding.progressBar.isVisible = false
                            }
                            is MainViewModel.RequestState.Error -> {
                                binding.rvPokedex.isVisible = true
                                binding.progressBar.isVisible = false
                            }
                            is MainViewModel.RequestState.Empty -> {
                                binding.rvPokedex.isVisible = true
                                binding.progressBar.isVisible = false
                            }
                            else -> Unit
                        }
                    }
                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == R.id.iAbout) {
            Toast.makeText(this, "About", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}