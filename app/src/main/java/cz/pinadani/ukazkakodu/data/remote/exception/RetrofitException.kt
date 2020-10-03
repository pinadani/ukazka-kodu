package cz.pinadani.ukazkakodu.data.remote.exception

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

/**
 *  Exceptions from Network layer
 */
class RetrofitException(
    message: String?,
    private val url: String?,
    private val response: Response<*>?,
    private val kind: Kind,
    exception: Throwable?,
    private val retrofit: Retrofit?
) : RuntimeException(message, exception) {

    private var error: Error? = null

    companion object {
        fun httpError(url: String, response: Response<*>, retrofit: Retrofit): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            val error = RetrofitException(
                message,
                url,
                response,
                Kind.HTTP,
                null,
                retrofit
            )
            error.deserializeServerError()
            return error
        }

        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(
                exception.message,
                null,
                null,
                Kind.NETWORK,
                exception,
                null
            )
        }

        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                exception.message,
                null,
                null,
                Kind.UNEXPECTED,
                exception,
                null
            )
        }
    }

    fun getUrl() = url
    fun getResponse() = response
    fun getKind() = kind
    fun getErrorMessage() = error?.message
    fun getErrorCode() = error?.message
    fun getRetrofit() = retrofit

    private fun deserializeServerError() {
        if (response?.errorBody() != null) {
            try {
                //error = getErrorBodyAs(Payload::class.java)?.error
            } catch (e: IOException) {

            }
        }
    }

    @Throws(IOException::class)
    private fun <T> getErrorBodyAs(type: Class<T>): T? {
        val errorBody = response?.errorBody()
        if (errorBody == null || retrofit == null) {
            return null
        }
        return try {
            val converter: Converter<ResponseBody, T> =
                retrofit.responseBodyConverter(type, arrayOfNulls<Annotation>(0))
            converter.convert(errorBody)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    enum class Kind {
        /** communicating to the server.  */
        NETWORK,
        /** non-200 HTTP.  */
        HTTP,
        UNEXPECTED,
        MAINTENANCE;
    }
}