package com.demo.flow.viewmodels

import androidx.lifecycle.ViewModel
import com.demo.flow.network.repository.PlaylistRepository


class SingleNetworkCallViewModel(
    private val repository : PlaylistRepository
) : ViewModel() {

}
