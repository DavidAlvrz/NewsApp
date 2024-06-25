package com.flashnews.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.flashnews.databinding.FragmentDashboardBinding
import com.flashnews.ui.adapter.NewsAdapter
import com.flashnews.ui.common.CommonViewModel
import com.flashnews.ui.home.DashboardViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchViewModel: DashboardViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        observeSearchResults()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(requireContext(),searchViewModel)
        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupSearchView() {
        binding.searchView.queryHint = "Buscar noticias..."
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.searchNews(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Do nothing
                return false
            }
        })

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.searchTitle.visibility = View.GONE
                val params = binding.searchView.layoutParams as LinearLayout.LayoutParams
                params.width = LinearLayout.LayoutParams.MATCH_PARENT
                binding.searchView.layoutParams = params
            } else {
                binding.searchTitle.visibility = View.VISIBLE
                val params = binding.searchView.layoutParams as LinearLayout.LayoutParams
                params.width = 0
                params.weight = 1f
                binding.searchView.layoutParams = params
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeSearchResults() {
        searchViewModel.articles.observe(viewLifecycleOwner, { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }
}
