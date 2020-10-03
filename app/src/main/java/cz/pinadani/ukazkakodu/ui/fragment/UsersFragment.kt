package cz.pinadani.ukazkakodu.ui.fragment

import android.os.Bundle
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vb.vm = vm
        setupAdapter()
        initObservers()
        vm.makeNetworkCall()
    }

    private fun setupAdapter() {
        vb.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter.setList(vm.getList())
        vb.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        vm.updateEvent.observe(viewLifecycleOwner, {
            if (it) {
                recyclerView.show()
                tvPlaceholder.hide()
                adapter.setList(vm.getList())
            }
        })
    }

}
