package com.satyaraj.cleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.satyaraj.cleanarchitecture.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private  val viewModel: MainViewModel by viewModels()
    private val message = view?.findViewById<TextView>(R.id.message)
    private val error = view?.findViewById<TextView>(R.id.error)
    private val empty = view?.findViewById<TextView>(R.id.empty)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getText()
        setViewModelListeners()
    }

    private fun setViewModelListeners() {
        viewModel.liveData.observe(viewLifecycleOwner, {
            message?.text = it.value
        })

        viewModel.liveEmptyData.observe(viewLifecycleOwner, {
            empty?.text = it
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            error?.text = it
        })
    }
}