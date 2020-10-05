package cz.pinadani.ukazkakodu.ui

import cz.pinadani.ukazkakodu.manager.CoroutinesManager
import cz.pinadani.ukazkakodu.ui.utils.ResourceProvider
import cz.pinadani.ukazkakodu.viewModel.UserDetailViewModel
import cz.pinadani.ukazkakodu.viewModel.UsersViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel { UsersViewModel(get(), get(), get()) }
    viewModel { UserDetailViewModel(get(), get(), get()) }
    single { ResourceProvider(androidApplication()) }
    single { CoroutinesManager() }
}