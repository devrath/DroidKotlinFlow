package com.demo.flow.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.flow.databinding.FragmentOperatorFilterBinding
import com.demo.flow.databinding.FragmentOperatorMapBinding
import com.demo.flow.presentation.base.BaseFragment
import com.demo.flow.presentation.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.presentation.view.uiState.OperatorMapUiState
import com.demo.flow.presentation.viewmodels.OperatorMapViewModel
import com.demo.flow.utils.extensions.gone
import com.demo.flow.utils.extensions.snack
import com.demo.flow.utils.extensions.visiable
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperatorMapFragment : BaseFragment() {

    private val viewModel by viewModel<OperatorMapViewModel>()

    private var _binding: FragmentOperatorMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext : Context

    private var listAdapter = MyPlaylistRecyclerViewAdapter(arrayListOf())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOperatorMapBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            viewModel.operatorMapUiState.collect {
                when (it) {
                    is OperatorMapUiState.Success -> {
                        binding.progressBar.gone()
                        binding.nameId.visiable()
                        binding.nameId.text = it.user
                    }
                    is OperatorMapUiState.Loading -> {
                        binding.progressBar.visiable()
                        binding.nameId.gone()
                    }
                    is OperatorMapUiState.Error -> {
                        binding.progressBar.gone()
                        binding.rootId.snack(it.message) {}
                    }
                    else -> Unit
                }
            }
        }
    }
}