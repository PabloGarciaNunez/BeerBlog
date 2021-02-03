package com.example.blogdelcervecero.ui.beer.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.blogdelcervecero.R
import com.example.blogdelcervecero.di.BeerApplication
import com.example.blogdelcervecero.ui.custom.LoadingView
import com.example.blogdelcervecero.usecases.ObtainBeersUseCase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class BeerListFragment: Fragment() {

    @Inject
    lateinit var viewModel: BeerListViewModel

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

        return inflater.inflate(R.layout.fragment_beer_list, container, false)
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

            addButton = findViewById(R.id.beer_list_add)
            list = findViewById(R.id.beer_list)
            listSwipe = findViewById(R.id.beer_list_swipe)
            loadingView = findViewById(R.id.loading_view)
        }
    }

    /**
     * Set on click listener to button
     */
    private fun setupButtons() {

        addButton.setOnClickListener { _view ->

            findNavController(_view).navigate(R.id.new_beer_nav)
        }

        listSwipe.setOnRefreshListener {

            viewModel.loadBeers()
        }
    }

    /**
     * Setup recycler view adapter
     */
    private fun setupAdapter() {

        val adapter = BeersAdapter()
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context)
        viewModel.beers.observe(viewLifecycleOwner, { beers ->

            adapter.setData(beers)
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
