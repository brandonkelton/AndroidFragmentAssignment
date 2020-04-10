package com.example.androidfragmentassignment.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.androidfragmentassignment.R
import kotlinx.android.synthetic.main.change_settings_fragment.*

class ChangeSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = ChangeSettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.change_settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(SettingsViewModel::class.java)
        }
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        button_SelectColor.setOnClickListener {
            val intent = Intent("msud.cs3013.ACTION_COLOR")
            startActivityForResult(intent, 1)
        }

        seekBar_planetSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.planetSize.postValue(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        checkbox_continent.setOnCheckedChangeListener { _, isChecked ->
            viewModel.hasContinents.postValue(isChecked)
            if (isChecked) {
                viewModel.hasIslands.postValue(false)
                viewModel.hasSmallContinents.postValue(false)
            }
        }
        checkbox_small_continent.setOnCheckedChangeListener { _, isChecked ->
            viewModel.hasSmallContinents.postValue(isChecked)
            if (isChecked) {
                viewModel.hasContinents.postValue(false)
                viewModel.hasIslands.postValue(false)
            }
        }
        checkbox_island.setOnCheckedChangeListener { _, isChecked ->
            viewModel.hasIslands.postValue(isChecked)
            if (isChecked) {
                viewModel.hasContinents.postValue(false)
                viewModel.hasSmallContinents.postValue(false)
            }
        }
        switch_population.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isPopulated.postValue(isChecked)
        }

    }

    private fun setupObservers() {
        viewModel.skyColor.observe(this, Observer {
            it?.let {
                main.setBackgroundColor(Color.rgb(it.red, it.green, it.blue))
            }
        })
        viewModel.planetSize.observe(this, Observer {
            it?.let {
                seekBar_planetSize.progress = it
                val params = imageView_planet.layoutParams
                params.width = 150 + it
                params.height = 150 + it
                imageView_planet.layoutParams = params
            }
        })
        viewModel.hasContinents.observe(this, Observer {
            it?.let {
                checkbox_continent.isChecked = it
            }
        })
        viewModel.hasSmallContinents.observe(this, Observer {
            it?.let {
                checkbox_small_continent.isChecked = it
            }
        })
        viewModel.hasIslands.observe(this, Observer {
            it?.let {
                checkbox_island.isChecked = it
            }
        })
        viewModel.isPopulated.observe(this, Observer {
            it?.let {
                switch_population.isChecked = it
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val skyColor = SkyColor()
            skyColor.red = data!!.getIntExtra("COLOR_RED", 0)
            skyColor.green = data!!.getIntExtra("COLOR_GREEN", 0)
            skyColor.blue = data!!.getIntExtra("COLOR_BLUE", 0)
            viewModel.skyColor.postValue(skyColor)
        }
    }
}
