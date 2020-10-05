package cz.pinadani.ukazkakodu.data.model.user


import com.google.gson.annotations.SerializedName

data class UsersDetailsResponse(
    @SerializedName("data")
    val data: ArrayList<UserDetail>
)