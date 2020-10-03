package cz.pinadani.ukazkakodu.extensions

import cz.pinadani.ukazkakodu.data.remote.exception.AppException
import cz.pinadani.ukazkakodu.data.remote.model.Resource

suspend fun <T> handleException(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.success(apiCall.invoke())
    } catch (throwable: Throwable) {
        Resource.error(AppException(throwable))
    }
}