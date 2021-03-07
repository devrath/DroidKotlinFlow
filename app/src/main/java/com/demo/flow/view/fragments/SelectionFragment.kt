package com.demo.flow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.demo.flow.R
import com.demo.flow.base.BaseFragment
import com.demo.flow.databinding.SelectionFragmentBinding

class SelectionFragment : BaseFragment() , View.OnClickListener {

    private var _binding: SelectionFragmentBinding? = null

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
        _binding = SelectionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setClickListener() {
        binding.startSingleNetworkCallActivity.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.startSingleNetworkCallActivity -> findNavController().navigate(R.id.nav_single_network_call_fragment)
        }
    }
}