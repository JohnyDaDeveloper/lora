package cz.johnyapps.lora.core.data

fun <T> T.result(): Result<T> {
    return Result.success(this)
}

fun <T> Result.Companion.fromCallable(
    callable: () -> T
): Result<T> {
    return try {
        success(callable.invoke())
    } catch (e: Exception) {
        failure(e)
    }
}