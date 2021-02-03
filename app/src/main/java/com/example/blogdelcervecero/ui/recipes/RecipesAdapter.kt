package com.example.blogdelcervecero.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogdelcervecero.R
import com.example.blogdelcervecero.model.Recipe


class RecipesAdapter(
    private var recipes: List<Recipe>? = null
) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        recipes?.let{ _recipes ->

            holder.bind(recipe = _recipes[position])
        }

    }

    override fun getItemCount(): Int {

        return recipes?.size ?: 0
    }

    fun setData(recipes: List<Recipe>?) {

        this.recipes = recipes
        notifyDataSetChanged()
    }

}

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.item_recipe_name)
    private val volume: TextView = view.findViewById(R.id.item_recipe_volume)

    fun bind(recipe: Recipe?) {

        recipe?.let { _recipe ->

            name.text = _recipe.name
            volume.text = _recipe.volume.toString()
        }
    }
}
