package com.maloac.pokedexproject.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maloac.pokedexproject.R
import com.maloac.pokedexproject.models.ResultsItem
import com.maloac.pokedexproject.screen.DetailActivity
import java.util.*

class PokedexListAdapter (private val dataSet: List<ResultsItem>) :
RecyclerView.Adapter<PokedexListAdapter.ViewHolder>()
{
    private var context: Context? = null

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
        holder.tvPokemonName.text = dataSet[position].name?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        holder.itemView.setOnClickListener { it ->
            val context = it.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("URL", dataSet[position].url)
            intent.putExtra("INDEX", position.toString())

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}