package com.example.testtaskrocket.presentation.baseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.databinding.ViewDataBinding

abstract class BaseFragment<DataBinding : ViewDataBinding>(@LayoutRes val layoutResId: Int) :
    Fragment() {
    lateinit var binding: DataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
            binding.lifecycleOwner = this@BaseFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureDataBinding()
    }

    private fun configureDataBinding() {
        setUpAdapters()
        setUpClicks()
        setUpViewModelCallbacks()
    }

    protected open fun setUpClicks() {}
    protected open fun setUpAdapters() {}
    protected open fun setUpViewModelCallbacks() {}
}