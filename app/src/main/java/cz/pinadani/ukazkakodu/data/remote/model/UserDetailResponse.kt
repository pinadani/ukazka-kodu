package cz.pinadani.ukazkakodu.data.remote.model


import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("data")
    val data: UserDetail
)