package com.test.machinetest.network

import okhttp3.ResponseBody

sealed class NetworkResponse<T>
class Success<T>(var data: T) : NetworkResponse<T>()
class Error<T>(var error: ResponseBody?) : NetworkResponse<T>()
