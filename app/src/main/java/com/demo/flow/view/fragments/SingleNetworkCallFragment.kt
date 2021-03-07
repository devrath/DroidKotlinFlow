package com.demo.flow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.flow.R
import com.demo.flow.base.BaseFragment
import com.demo.flow.databinding.FragmentSingleNetworkCallBinding
import com.demo.flow.viewmodels.SingleNetworkCallViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingleNetworkCallFragment : BaseFragment() {

    private val viewModel by viewModel<SingleNetworkCallViewModel>()

    private var _binding: FragmentSingleNetworkCallBinding? = null

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
        return inflater.inflate(R.layout.fragment_single_network_call, container, false)
    }
}