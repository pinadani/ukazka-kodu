package cz.pinadani.ukazkakodu.data.remote

import cz.pinadani.ukazkakodu.data.remote.model.UsersDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int): UsersDetailsResponse
}