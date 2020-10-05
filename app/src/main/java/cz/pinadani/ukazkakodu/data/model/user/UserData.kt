package cz.pinadani.ukazkakodu.data.model.user


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserData(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "avatar")
    val avatar: String
) {
    constructor(userDetail: UserDetail) : this(
        userDetail.id,
        userDetail.email,
        userDetail.firstName,
        userDetail.lastName,
        userDetail.avatar
    )
}