package cz.pinadani.ukazkakodu.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import cz.pinadani.ukazkakodu.R
import cz.pinadani.ukazkakodu.adapter.OnItemActionListener
import cz.pinadani.ukazkakodu.adapter.UserViewType
import cz.pinadani.ukazkakodu.adapter.ViewType
import cz.pinadani.ukazkakodu.data.remote.model.Resource
import cz.pinadani.ukazkakodu.data.remote.model.UserDetail
import cz.pinadani.ukazkakodu.data.remote.users.UsersRepo
import cz.pinadani.ukazkakodu.livedata.SingleLiveEvent
import cz.pinadani.ukazkakodu.manager.CoroutinesManager
import cz.pinadani.ukazkakodu.ui.utils.ResourceProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class UsersViewModel(
    private val resourceProvider: ResourceProvider,
    private val coroutinesManager: CoroutinesManager,
    private val usersRepo: UsersRepo
) : ViewModel(), UserDetailClickListener<UserDetail> {

    companion object {
        private const val logTag = "UsersViewModel"
    }

    val updateEvent = SingleLiveEvent<Boolean>()
    val textObservable = ObservableField<String>()
    private val users = ArrayList<UserDetail>()
    private val list = ArrayList<ViewType<*>>()

    fun getList(): List<ViewType<*>> {
        list.clear()
        users.forEach {
            list.add(UserViewType(it))
        }
        return list
    }

    fun makeNetworkCall() {
        Log.i(logTag, "Set TextView using DataBinding")
        textObservable.set(resourceProvider.getString(R.string.lorem_long))

        coroutinesManager.ioScope.launch {
            val deferredList = ArrayList<Deferred<*>>()

            for (i in 1..2) {
                deferredList.add(async {
                    val result = usersRepo.getUsers(i)
                    if (result.status == Resource.Status.SUCCESS) {
                        users.addAll(result.data!!.data)
                    }
                })
            }

            deferredList.joinAll()
            Log.i(logTag, "All Networks calls complete")

            updateEvent.postValue(true)
            Log.i(logTag, "Update UI")
        }
    }


    override fun onItemClicked(item: UserDetail) {
        Log.d(logTag, "click")
    }
}

interface UserDetailClickListener<T> : OnItemActionListener<T> {}
