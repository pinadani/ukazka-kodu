package cz.pinadani.ukazkakodu.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.pinadani.ukazkakodu.R
import cz.pinadani.ukazkakodu.adapter.ViewType
import cz.pinadani.ukazkakodu.adapter.ViewTypeAdapter
import cz.pinadani.ukazkakodu.base.DataBindingFragment
import cz.pinadani.ukazkakodu.databinding.FragmentUsersBinding
import cz.pinadani.ukazkakodu.extensions.hide
import cz.pinadani.ukazkakodu.extensions.lazyN
import cz.pinadani.ukazkakodu.extensions.show
import cz.pinadani.ukazkakodu.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.android.ext.android.inject


class UsersFragment : DataBindingFragment<FragmentUsersBinding>() {

    private val vm by inject<UsersViewModel>()

    private val adapter by lazyN { ViewTypeAdapter<ViewType<*>>(onItemActionListener = vm) }

    override fun layoutId() = R.layout.fragment_users

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val initView = !hasInitializedRootView
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (initView) {
            vb.vm = vm
            setupAdapter()
            initObservers()
            vm.makeNetworkCall()
        } else {
            initObservers()
        }

        return view
    }

    private fun setupAdapter() {
        vb.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter.setList(vm.getList())
        vb.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        vm.updateEvent.observe(viewLifecycleOwner, {
            loading.hide()
            if (it) {
                recyclerView.show()
                adapter.setList(vm.getList())
            } else {
                noData.show()
            }
        })
        vm.moveToDetail.observe(viewLifecycleOwner, {
            findNavController().navigate(UsersFragmentDirections.showDetail(it))
        })
    }

}
