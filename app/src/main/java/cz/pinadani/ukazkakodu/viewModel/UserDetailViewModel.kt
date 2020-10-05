package cz.pinadani.ukazkakodu.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
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

class UserDetailViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase,
    private val coroutinesManager: CoroutinesManager,
    private val usersRepo: UsersRepo
) : ViewModel() {

    companion object {
        private const val logTag = "UsersViewModel"
    }

    val updateEvent = SingleLiveEvent<Boolean>()
    lateinit var user: UserDetail

    fun makeNetworkCall(id: Int) {
        Log.i(logTag, "Set TextView using DataBinding")

        coroutinesManager.ioScope.launch {
            val deferredList = ArrayList<Deferred<*>>()

            deferredList.add(async {
                val userData = appDatabase.userDao().getUser(id)
                if (userData == null) {
                    val result = usersRepo.getUser(id)
                    if (result.status == Resource.Status.SUCCESS) {
                        user = result.data!!.data
                        appDatabase.userDao().insert(UserData(user))
                    }
                } else {
                    user = UserDetail(userData)
                }
            })

            deferredList.joinAll()

            updateEvent.postValue(::user.isInitialized)
            Log.i(logTag, "Update UI")
        }
    }
}

