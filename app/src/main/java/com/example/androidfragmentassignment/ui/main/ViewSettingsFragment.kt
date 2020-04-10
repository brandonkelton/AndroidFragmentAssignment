package com.example.androidfragmentassignment.ui.main


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.view_settings_fragment.*
import android.widget.TextView
import androidx.lifecycle.Observer

import com.example.androidfragmentassignment.R

class ViewSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = ViewSettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(SettingsViewModel::class.java)
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.skyColor.observe(this, Observer {
            it?.let {
                textView_skycolor_value.text = "RGB: " + it.red + ", " + it.green + ", " + it.blue
            }
        })
        viewModel.planetSize.observe(this, Observer {
            it?.let {
                textView_planetSize_value.text = it.toString()
            }
        })
        viewModel.hasContinents.observe(this, Observer {
            it?.let {
                if (it) textView_landTypes.text = "Continents"
            }
        })
        viewModel.hasSmallContinents.observe(this, Observer {
            it?.let {
                if (it) textView_landTypes.text = "Small Continents"
            }
        })
        viewModel.hasIslands.observe(this, Observer {
            it?.let {
                if (it) textView_landTypes.text = "Islands"
            }
        })
        viewModel.isPopulated.observe(this, Observer {
            it?.let {
                textView_isPopulated.text = if (it) "YES" else "NO"
            }
        })
    }
}
