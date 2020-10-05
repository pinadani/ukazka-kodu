package cz.pinadani.ukazkakodu.data.model.user


import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String


) {
    constructor(userData: UserData) : this(
        userData.id,
        userData.email,
        userData.firstName,
        userData.lastName,
        userData.avatar
    )
}