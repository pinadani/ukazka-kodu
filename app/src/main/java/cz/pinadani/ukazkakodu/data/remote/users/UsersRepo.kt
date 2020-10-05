package cz.pinadani.ukazkakodu.data.remote.users

import cz.pinadani.ukazkakodu.data.remote.ApiService
import cz.pinadani.ukazkakodu.data.remote.model.Resource
import cz.pinadani.ukazkakodu.data.remote.model.UserDetailResponse
import cz.pinadani.ukazkakodu.data.remote.model.UsersDetailsResponse
import cz.pinadani.ukazkakodu.extensions.handleException

class UsersRepo(private val apiService: ApiService) {
    suspend fun getUsers(page: Int): Resource<UsersDetailsResponse> {
        return handleException {
            apiService.getUsers(page)
        }
    }

    suspend fun getUser(id: Int): Resource<UserDetailResponse> {
        return handleException {
            apiService.getUser(id)
        }
    }
}