package com.demo.flow.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.demo.flow.databinding.FragmentOperatorReduceBinding
import com.demo.flow.presentation.base.BaseFragment
import com.demo.flow.presentation.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.presentation.view.uiState.OperatorReduceUiState
import com.demo.flow.presentation.viewmodels.OperatorReduceViewModel
import com.demo.flow.utils.extensions.gone
import com.demo.flow.utils.extensions.snack
import com.demo.flow.utils.extensions.visiable
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperatorReduceFragment : BaseFragment() {

    private val viewModel by viewModel<OperatorReduceViewModel>()

    private var _binding: FragmentOperatorReduceBinding? = null
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
        _binding = FragmentOperatorReduceBinding.inflate(layoutInflater)
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
        viewModel.getTotalPriceOfBooks()
    }

    private fun setupObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.operatorReduceUiState.collect {
                when (it) {
                    is OperatorReduceUiState.Success -> {
                        binding.progressBar.gone()
                        binding.nameId.visiable()
                        binding.nameId.text = it.result
                    }
                    is OperatorReduceUiState.Loading -> {
                        binding.progressBar.visiable()
                        binding.nameId.gone()
                    }
                    is OperatorReduceUiState.Error -> {
                        binding.progressBar.gone()
                        binding.rootId.snack(it.message) {}
                    }
                    else -> Unit
                }
            }
        }
    }
}