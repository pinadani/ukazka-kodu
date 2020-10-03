package cz.pinadani.ukazkakodu.data.remote.exception


open class AppException(private val exception: Throwable?) : Exception() {

    constructor(message: String) : this(Throwable(message))

    fun getErrorCode(): String? =
        if (exception is RetrofitException) exception.getErrorCode() else exception?.message

    fun getErrorMessage(): String? =
        if (exception is RetrofitException) exception.getErrorMessage() else exception?.message

    fun isNetworkError() =
        exception is RetrofitException && exception.getKind() == RetrofitException.Kind.NETWORK
}
