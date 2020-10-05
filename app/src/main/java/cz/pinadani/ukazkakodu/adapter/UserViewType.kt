package cz.pinadani.ukazkakodu.adapter

import cz.pinadani.ukazkakodu.R
import cz.pinadani.ukazkakodu.data.model.user.UserDetail

data class UserViewType(private val model: UserDetail) : ViewType<UserDetail> {
    override fun layoutId(): Int = R.layout.layout_user_row
    override fun data(): UserDetail = model
}