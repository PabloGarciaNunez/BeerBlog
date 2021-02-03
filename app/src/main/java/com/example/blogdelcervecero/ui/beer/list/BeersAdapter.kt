package com.example.blogdelcervecero.ui.beer.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogdelcervecero.R
import com.example.blogdelcervecero.model.Beer

class BeersAdapter(
    var beers : List<Beer>? = null
) : RecyclerView.Adapter<BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {

        return BeerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {

        beers?.let { _beer ->

            holder.bind(_beer[position])
        }
    }

    override fun getItemCount(): Int {

        return beers?.size ?: 0
    }

    fun setData(beers: List<Beer>) {

        this.beers = beers
        notifyDataSetChanged()
    }
}

class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.item_beer_name)
    private val style: TextView = view.findViewById(R.id.item_beer_style)
    private val grades: TextView = view.findViewById(R.id.item_beer_grades)

    fun bind(beer: Beer?) {

        beer?.let {

            name.text = it.name
            style.text = it.style
            grades.text = it.grades.toString()
        }
    }
}

