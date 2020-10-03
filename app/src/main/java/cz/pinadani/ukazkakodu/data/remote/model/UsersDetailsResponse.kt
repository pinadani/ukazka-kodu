package cz.pinadani.ukazkakodu.data.remote.model


import com.google.gson.annotations.SerializedName

data class UsersDetailsResponse(
    @SerializedName("data")
    val data: ArrayList<UserDetail>
)