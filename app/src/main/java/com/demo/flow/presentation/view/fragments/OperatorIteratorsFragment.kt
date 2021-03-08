package com.demo.flow.presentation.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.demo.flow.R
import com.demo.flow.presentation.base.BaseFragment
import com.demo.flow.databinding.FragmentOperatorIteratorBinding
import com.demo.flow.utils.extensions.gone
import com.demo.flow.utils.extensions.snack
import com.demo.flow.utils.extensions.toast
import com.demo.flow.utils.extensions.visiable
import com.demo.flow.presentation.view.uiState.OperatorIteratorUiState
import com.demo.flow.presentation.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.presentation.view.uiActions.OperatorIteratorsAction
import com.demo.flow.presentation.viewmodels.OperatorIteratorsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperatorIteratorsFragment : BaseFragment(), View.OnClickListener{

    private val viewModel by viewModel<OperatorIteratorsViewModel>()

    private var _binding: FragmentOperatorIteratorBinding? = null
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
        _binding = FragmentOperatorIteratorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupUI() {
        binding.btnIteratorId.setOnClickListener(this)
        binding.btnForId.setOnClickListener(this)
        binding.btnForEachId.setOnClickListener(this)
        binding.btnForEachIndexedId.setOnClickListener(this)
    }

    private fun setupObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.operatorIteratorUiState.collect {
                when (it) {
                    is OperatorIteratorUiState.Success -> {
                        binding.progressBar.gone()
                        binding.operatorsContainerId.visiable()
                    }
                    is OperatorIteratorUiState.Loading -> {
                        binding.progressBar.visiable()
                        binding.operatorsContainerId.gone()
                    }
                    is OperatorIteratorUiState.Error -> {
                        binding.progressBar.gone()
                        binding.rootId.snack(it.message) {}
                    }
                    is OperatorIteratorUiState.Empty -> {
                        binding.progressBar.gone()
                        binding.operatorsContainerId.visiable()
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onClick(v: View?) {

            when(view?.id){
                R.id.btnIteratorId -> lifecycleScope.launch {
                    viewModel.operatorIteratorsAction.send(OperatorIteratorsAction.OperatorActionIterator(""))
                }
                R.id.btnForId -> lifecycleScope.launch {
                    viewModel.operatorIteratorsAction.send(OperatorIteratorsAction.OperatorActionFor(""))
                }
                R.id.btnForEachId -> lifecycleScope.launch {
                    viewModel.operatorIteratorsAction.send(OperatorIteratorsAction.OperatorActionForEach(""))
                }
                R.id.btnForEachIndexedId -> lifecycleScope.launch {
                    viewModel.operatorIteratorsAction.send(OperatorIteratorsAction.OperatorActionForEachIndexed(""))
                }
            }
        }

}