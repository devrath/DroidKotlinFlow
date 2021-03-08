package com.demo.flow.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.flow.databinding.FragmentOperatorPartitionBinding
import com.demo.flow.presentation.base.BaseFragment
import com.demo.flow.presentation.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.presentation.view.uiState.OperatorFilterUiState
import com.demo.flow.presentation.view.uiState.OperatorPartitionUiState
import com.demo.flow.presentation.viewmodels.OperatorFilterViewModel
import com.demo.flow.presentation.viewmodels.OperatorPartitionViewModel
import com.demo.flow.utils.extensions.gone
import com.demo.flow.utils.extensions.snack
import com.demo.flow.utils.extensions.visiable
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperatorPartitionFragment : BaseFragment() {

    private val viewModel by viewModel<OperatorPartitionViewModel>()

    private var _binding: FragmentOperatorPartitionBinding? = null
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
        _binding = FragmentOperatorPartitionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
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

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(mContext)
        listAdapter = MyPlaylistRecyclerViewAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = listAdapter
    }

    private fun setupObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.operatorPartitionUiState.collect {
                when (it) {
                    is OperatorPartitionUiState.Success -> {
                        binding.progressBar.gone()
                        binding.recyclerView.visiable()
                        listAdapter.updateList(it.usersList)
                    }
                    is OperatorPartitionUiState.Loading -> {
                        binding.progressBar.visiable()
                        binding.recyclerView.gone()
                    }
                    is OperatorPartitionUiState.Error -> {
                        binding.progressBar.gone()
                        binding.rootId.snack(it.message) {}
                    }
                    else -> Unit
                }
            }
        }
    }
}