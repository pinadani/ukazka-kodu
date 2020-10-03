package cz.pinadani.ukazkakodu.data.remote.users

import cz.pinadani.ukazkakodu.data.remote.ApiService
import cz.pinadani.ukazkakodu.data.remote.createWebService
import cz.pinadani.ukazkakodu.data.remote.provideRetrofit
import org.koin.dsl.module


val remoteModule = module {

    single {
        provideRetrofit(
            get(),
            "https://reqres.in"
        )
    }

    single { UsersRepo(get()) }

    factory {
        createWebService<ApiService>(
            get()
        )
    }
}