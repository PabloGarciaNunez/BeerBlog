package com.example.blogdelcervecero.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.blogdelcervecero.R
import com.example.blogdelcervecero.di.BeerApplication
import com.example.blogdelcervecero.ui.beer.list.BeerListViewModel
import com.example.blogdelcervecero.ui.beer.list.BeersAdapter
import com.example.blogdelcervecero.ui.custom.LoadingView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class RecipesListFragment: Fragment() {

    @Inject
    lateinit var viewModel: RecipesListViewModel

    private lateinit var addButton: FloatingActionButton
    private lateinit var list: RecyclerView
    private lateinit var listSwipe: SwipeRefreshLayout
    private lateinit var loadingView: LoadingView

    //region LIFE_CYCLE_METHODS

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        (activity?.applicationContext as BeerApplication).component.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recipes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        this.bind(view)
        this.setupButtons()
        this.setupAdapter()
    }

    //endregion

    //region PRIVATE_METHODS

    /**
     * Bind views
     *
     * @param view - fragment view
     */
    private fun bind(view: View) {

        view.run {

            addButton = findViewById(R.id.recipe_list_add)
            list = findViewById(R.id.recipe_list)
            listSwipe = findViewById(R.id.recipe_list_swipe)
            loadingView = findViewById(R.id.loading_view)
        }
    }

    /**
     * Set on click listener to button
     */
    private fun setupButtons() {

        listSwipe.setOnRefreshListener {

            viewModel.loadRecipes()
        }
    }

    /**
     * Setup recycler view adapter
     */
    private fun setupAdapter() {

        val adapter = RecipesAdapter()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context)
        viewModel.recipes.observe(viewLifecycleOwner, { recipes ->

            adapter.setData(recipes)
            list.scheduleLayoutAnimation()
        })

        viewModel.dataLoading.observe(viewLifecycleOwner, { isLoading ->

            listSwipe.isRefreshing = false
            when (isLoading) {

                true -> loadingView.show()
                false -> loadingView.hide()
            }
        })
    }

    //endregion
}