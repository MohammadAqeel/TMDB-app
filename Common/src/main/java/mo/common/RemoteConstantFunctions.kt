package mo.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeout
import mo.common.RemoteConstants.CONNECTION_TIMEOUT
import mo.common.RemoteConstants.GENERAL_ERROR_MESSAGE
import mo.common.RemoteConstants.HTTP_EXCEPTION_MESSAGE
import mo.common.RemoteConstants.IO_EXCEPTION_MESSAGE
import mo.common.RemoteConstants.TIMEOUT_EXCEPTION_MESSAGE
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> returnResult(
    content: suspend CoroutineScope.() -> Result<T>
): Result<T> {
    return try {
        withTimeout(CONNECTION_TIMEOUT) { coroutineScope(content) }
    } catch (ioException: IOException) {
        Result.Error(IO_EXCEPTION_MESSAGE)
    } catch (httpException: HttpException) {
        Result.Error(HTTP_EXCEPTION_MESSAGE)
    } catch (timeOut: TimeoutCancellationException) {
        Result.Error(TIMEOUT_EXCEPTION_MESSAGE)
    } catch (e: Exception) {
        Result.Error(GENERAL_ERROR_MESSAGE)
    }
}

