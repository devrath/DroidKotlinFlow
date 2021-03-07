package com.demo.flow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.flow.R
import com.demo.flow.base.BaseFragment
import com.demo.flow.databinding.FragmentSingleNetworkCallBinding
import com.demo.flow.databinding.SelectionFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

class SingleNetworkCallFragment : BaseFragment() {

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