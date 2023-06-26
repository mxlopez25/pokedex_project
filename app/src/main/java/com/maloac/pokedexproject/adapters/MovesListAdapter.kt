package com.maloac.pokedexproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.models.PokedexData
import java.util.*

class MovesListAdapter(private val dataSet: List<PokedexData.Move>) :
RecyclerView.Adapter<MovesListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvPokemonMove: TextView

        init {
            tvPokemonMove = view.findViewById(R.id.tvPokemonMove)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.move_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPokemonMove.text = dataSet[position].name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}