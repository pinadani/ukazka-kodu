package cz.pinadani.ukazkakodu.ui.activity

import cz.pinadani.ukazkakodu.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
}