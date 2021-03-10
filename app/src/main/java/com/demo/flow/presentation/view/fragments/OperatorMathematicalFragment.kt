package com.demo.flow.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.demo.flow.databinding.FragmentOperatorMathematicalBinding
import com.demo.flow.presentation.base.BaseFragment
import com.demo.flow.presentation.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.presentation.view.uiState.OperatorMathematicalUiState
import com.demo.flow.presentation.viewmodels.OperatorMathematicalViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperatorMathematicalFragment  : BaseFragment() {

    private val viewModel by viewModel<OperatorMathematicalViewModel>()

    private var _binding: FragmentOperatorMathematicalBinding? = null
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
        _binding = FragmentOperatorMathematicalBinding.inflate(layoutInflater)
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
        //viewModel.fetchUsers()
    }

    private fun setupObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.operatorMathematicalUiState.collect {
                when (it) {
                    is OperatorMathematicalUiState.Success -> {
                        //binding.nameId.text = it.result
                    }
                    else -> Unit
                }
            }
        }
    }
}