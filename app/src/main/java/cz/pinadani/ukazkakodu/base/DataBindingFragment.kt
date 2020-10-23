package cz.pinadani.ukazkakodu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Fragment Binding
 */
abstract class DataBindingFragment<VB : ViewDataBinding> : BaseFragment() {
    var hasInitializedRootView = false
    lateinit var vb: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::vb.isInitialized) {
            vb = DataBindingUtil.inflate(inflater, layoutId(), container, false)
            hasInitializedRootView = true
        }
        retainInstance = true
        return vb.root
    }
}