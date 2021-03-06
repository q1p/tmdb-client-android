package me.venko.tmdbclient.presentation.discovery

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_discovery.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_discovery

    private lateinit var discoveryViewModel: DiscoveryViewModel
    private var discoveryAdapter: DiscoveryAdapter? = null
    private var pendingSavedState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pendingSavedState = savedInstanceState
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        discoveryViewModel = ViewModelProviders.of(this).get(DiscoveryViewModel::class.java)
        setupDiscovery()
    }

    private fun setupDiscovery() {

        if (discoveryAdapter == null) {
            val sections = listOf(
                DiscoverySection(
                    R.string.tv_discovery_section_title_popular,
                    discoveryViewModel.popularContentState,
                    discoveryViewModel.popularMovies
                ),
                DiscoverySection(
                    R.string.tv_discovery_section_title_top_rated,
                    discoveryViewModel.topRatedContentState,
                    discoveryViewModel.topRatedMovies
                ),
                DiscoverySection(
                    R.string.tv_discovery_section_title_top_revenue,
                    discoveryViewModel.topRevenueContentState,
                    discoveryViewModel.topRevenueMovies
                ),
                DiscoverySection(
                    R.string.tv_discovery_section_title_most_recent,
                    discoveryViewModel.mostRecentContentState,
                    discoveryViewModel.mostRecentMovies
                )
            )

            discoveryAdapter = DiscoveryAdapter(requireContext(), viewLifecycleOwner, sections) {
                discoveryViewModel.displayMovieDetails(it)
            }
        }

        rvDiscovery.adapter = discoveryAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        discoveryAdapter?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        discoveryAdapter?.onStart(pendingSavedState)
        pendingSavedState = null
    }

    override fun onStop() {
        super.onStop()
        discoveryAdapter?.onStop()
    }

    companion object {
        fun newInstance() = DiscoveryFragment()
    }
}
