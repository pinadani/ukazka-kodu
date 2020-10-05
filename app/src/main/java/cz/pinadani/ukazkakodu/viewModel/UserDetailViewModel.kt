package cz.pinadani.ukazkakodu.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
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

class UserDetailViewModel(
    private val resourceProvider: ResourceProvider,
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
                val result = usersRepo.getUser(id)
                if (result.status == Resource.Status.SUCCESS) {
                    user = result.data!!.data
                }
            })

            deferredList.joinAll()

            updateEvent.postValue(true)
            Log.i(logTag, "Update UI")
        }
    }
}

