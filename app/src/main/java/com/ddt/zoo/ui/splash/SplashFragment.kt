package com.ddt.zoo.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.ddt.zoo.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class SplashFragment : Fragment() {

    private val splashViewModel:SplashViewModel  by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.switchHome(SplashFragmentDirections.actionSplashFragmentToHomeFragment())

        splashViewModel.switchNav.observe(viewLifecycleOwner){
             findNavController().navigate(it)
        }

    }

}
