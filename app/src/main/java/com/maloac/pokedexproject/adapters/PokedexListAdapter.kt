package com.maloac.pokedexproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.models.ResultsItem

class PokedexListAdapter (private val dataSet: List<ResultsItem>) :
RecyclerView.Adapter<PokedexListAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvPokemonName: TextView

        init {
            tvPokemonName = view.findViewById(R.id.tvPokemonName)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokedex_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPokemonName.text = dataSet[position].name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}