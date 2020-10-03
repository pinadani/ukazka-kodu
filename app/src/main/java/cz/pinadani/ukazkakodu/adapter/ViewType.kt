package cz.pinadani.ukazkakodu.adapter

import androidx.annotation.LayoutRes

/**
 *  Ultimate view types for [RecyclerView]
 */
interface ViewType<out T> {

    @LayoutRes
    fun layoutId(): Int

    fun data(): T

    fun isUserInteractionEnabled() = true
}