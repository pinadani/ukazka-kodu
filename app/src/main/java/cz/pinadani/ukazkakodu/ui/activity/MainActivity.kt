package cz.pinadani.ukazkakodu.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.navigation.findNavController
import cz.pinadani.ukazkakodu.R
import cz.pinadani.ukazkakodu.base.DataBindingActivity
import cz.pinadani.ukazkakodu.databinding.ActivityMainBinding
import cz.pinadani.ukazkakodu.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    private val mainVM by inject<MainViewModel>()

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb.viewModel = mainVM
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBundle("nav_state", container.findNavController().saveState())
    }
}