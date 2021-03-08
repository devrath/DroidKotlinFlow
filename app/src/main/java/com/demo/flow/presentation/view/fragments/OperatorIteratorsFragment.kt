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
import com.demo.flow.presentation.viewmodels.OperatorIteratorsViewModel
import kotlinx.coroutines.flow.collect
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
                        listAdapter.updateList(it.usersList)
                    }
                    is OperatorIteratorUiState.Loading -> {
                        binding.progressBar.visiable()
                        binding.operatorsContainerId.gone()
                    }
                    is OperatorIteratorUiState.Error -> {
                        binding.progressBar.gone()
                        binding.rootId.snack(it.message) {}
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(view?.id){
            R.id.btnIteratorId -> toast("ITERATOR - OPTION",true,mContext)
            R.id.btnForId ->  toast("FOR - OPTION",true,mContext)
            R.id.btnForEachId ->  toast("FOR EACH - OPTION",true,mContext)
            R.id.btnForEachIndexedId ->  toast("FOR EACH INDEXED - OPTION",true,mContext)
        }
    }
}