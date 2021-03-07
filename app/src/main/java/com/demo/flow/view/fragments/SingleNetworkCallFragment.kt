package com.demo.flow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.demo.flow.R
import com.demo.flow.base.BaseFragment
import com.demo.flow.databinding.FragmentSingleNetworkCallBinding
import com.demo.flow.utils.extensions.snack
import com.demo.flow.view.actions.PlaylistUiState
import com.demo.flow.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.viewmodels.SingleNetworkCallViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingleNetworkCallFragment : BaseFragment() {

    private val viewModel by viewModel<SingleNetworkCallViewModel>()

    private var _binding: FragmentSingleNetworkCallBinding? = null
    private val listAdapter = MyPlaylistRecyclerViewAdapter()

    private val binding get() = _binding!!

    private lateinit var mContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleNetworkCallBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setupUI()
        setupObserver()
        initiateApi()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initiateApi() {
        viewModel.fetchUsers()
    }

    private fun setupObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.playlistUiState.collect {
                when (it) {
                    is PlaylistUiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is PlaylistUiState.Error -> {
                        binding.rootId.snack(it.message) {}
                        binding.progressBar.visibility = View.GONE
                    }
                    is PlaylistUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }


}