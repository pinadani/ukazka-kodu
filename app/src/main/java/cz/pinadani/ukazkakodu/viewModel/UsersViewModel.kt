package cz.pinadani.ukazkakodu.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import cz.pinadani.ukazkakodu.adapter.OnItemActionListener
import cz.pinadani.ukazkakodu.adapter.UserViewType
import cz.pinadani.ukazkakodu.adapter.ViewType
import cz.pinadani.ukazkakodu.data.AppDatabase
import cz.pinadani.ukazkakodu.data.model.Resource
import cz.pinadani.ukazkakodu.data.model.user.UserData
import cz.pinadani.ukazkakodu.data.model.user.UserDetail
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
    private val appDatabase: AppDatabase,
    private val coroutinesManager: CoroutinesManager,
    private val usersRepo: UsersRepo
) : ViewModel(), UserDetailClickListener<UserDetail> {

    companion object {
        private const val logTag = "UsersViewModel"
    }

    val updateEvent = SingleLiveEvent<Boolean>()
    val moveToDetail = SingleLiveEvent<Int>()
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

        coroutinesManager.ioScope.launch {
            val deferredList = ArrayList<Deferred<*>>()

            for (i in 1..2) {
                deferredList.add(async {
                    val result = usersRepo.getUsers(i)
                    if (result.status == Resource.Status.SUCCESS) {
                        users.addAll(result.data!!.data)
                        result.data.data.forEach {
                            appDatabase.userDao().insert(UserData(it))
                        }
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
        moveToDetail.postValue(item.id)
    }
}

interface UserDetailClickListener<T> : OnItemActionListener<T> {}
