package cz.pinadani.ukazkakodu.ui.fragment

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import cz.pinadani.ukazkakodu.R
import cz.pinadani.ukazkakodu.base.DataBindingFragment
import cz.pinadani.ukazkakodu.databinding.FragmentUserDetailBinding
import cz.pinadani.ukazkakodu.extensions.hide
import cz.pinadani.ukazkakodu.extensions.show
import cz.pinadani.ukazkakodu.viewModel.UserDetailViewModel
import kotlinx.android.synthetic.main.fragment_user_detail.*
import org.koin.android.ext.android.inject


class UserDetailFragment : DataBindingFragment<FragmentUserDetailBinding>() {

    private val vm by inject<UserDetailViewModel>()


    override fun layoutId() = R.layout.fragment_user_detail
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vb.vm = vm
        initObservers()
        vm.makeNetworkCall(args.id)
    }


    private fun initObservers() {
        vm.updateEvent.observe(viewLifecycleOwner, {
            if (it) {
                vb.model = vm.user
                loading.hide()
                contentLayout.show()
            }
        })
    }

}
