package com.example.androidfragmentassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Switch
import androidx.lifecycle.ViewModelProviders
import com.example.androidfragmentassignment.ui.main.ChangeSettingsFragment
import com.example.androidfragmentassignment.ui.main.SettingsViewModel
import com.example.androidfragmentassignment.ui.main.ViewSettingsFragment
import kotlinx.android.synthetic.main.change_settings_fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Intersteller Vacation"
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ViewSettingsFragment.newInstance())
                .commitNow()
        }
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_ViewSettings -> switchToViewSettings()
            R.id.menu_ChangeSettings -> switchToChangeSettings()
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun switchToViewSettings(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ViewSettingsFragment.newInstance())
            .commitNow()

        return true
    }

    fun switchToChangeSettings(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ChangeSettingsFragment.newInstance())
            .commitNow()

        return true
    }

//    fun onContinentClicked(view: View) {
//        var checkbox = view as CheckBox
//        viewModel.continent = checkbox.isChecked
////        checkbox_island.isChecked = true
//    }
//
//    fun onSmallContinentClicked(view: View) {
//        var checkbox = view as CheckBox
//        viewModel.smallContinent = checkbox.isChecked
//    }
//
//    fun onIslandClicked(view: View) {
//        var checkbox = view as CheckBox
//        viewModel.islands = checkbox.isChecked
//    }
//
//    fun onPopulatedChange(view: View) {
//        var switch = view as Switch
//        viewModel.populated = switch.isChecked
//    }
}
