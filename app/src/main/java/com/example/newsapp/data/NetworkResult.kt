package com.example.newsapp.data

sealed class NetworkResult<T>(
    val data : T? = null,
    val message : String? = null
) {
    class OnSuccess<T>(data: T?) : NetworkResult<T>(data)
    class OnFailure<T>(data: T? = null,message: String?) : NetworkResult<T>(data,message)
    class OnLoading<T> : NetworkResult<T>()
}