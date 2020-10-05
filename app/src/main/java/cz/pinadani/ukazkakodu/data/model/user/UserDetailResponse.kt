package cz.pinadani.ukazkakodu.data.model.user


import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("data")
    val data: UserDetail
)