package com.demo.flow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.flow.R
import com.demo.flow.base.BaseFragment
import com.demo.flow.databinding.FragmentOperatorFilterBinding
import com.demo.flow.databinding.FragmentOperatorIteratorBinding
import com.demo.flow.utils.extensions.gone
import com.demo.flow.utils.extensions.snack
import com.demo.flow.utils.extensions.toast
import com.demo.flow.utils.extensions.visiable
import com.demo.flow.view.actions.OperatorFilterUiState
import com.demo.flow.view.adapters.MyPlaylistRecyclerViewAdapter
import com.demo.flow.viewmodels.OperatorFilterViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class OperatorIteratorsFragment : BaseFragment(), View.OnClickListener{

    private val viewModel by viewModel<OperatorFilterViewModel>()

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
            viewModel.operatorFilterUiState.collect {
                when (it) {
                    is OperatorFilterUiState.Success -> {
                        binding.progressBar.gone()
                        binding.operatorsContainerId.visiable()
                        listAdapter.updateList(it.usersList)
                    }
                    is OperatorFilterUiState.Loading -> {
                        binding.progressBar.visiable()
                        binding.operatorsContainerId.gone()
                    }
                    is OperatorFilterUiState.Error -> {
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
            R.id.btnIteratorId -> toast("FOR ITERATOR - OPTION",true,mContext)
            R.id.btnForId ->  toast("FOR - OPTION",true,mContext)
            R.id.btnForEachId ->  toast("FOR EACH - OPTION",true,mContext)
            R.id.btnForEachIndexedId ->  toast("FOR EACH INDEXED - OPTION",true,mContext)
        }
    }
}