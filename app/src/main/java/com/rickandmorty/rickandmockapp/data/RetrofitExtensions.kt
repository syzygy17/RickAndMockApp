package com.rickandmorty.rickandmockapp.data

import com.rickandmorty.rickandmockapp.domain.Response
import com.rickandmorty.rickandmockapp.utils.SERVER_EXCEPTION_MESSAGE
import retrofit2.Response as RetrofitResponse


fun <Api : Any, Domain : Any> RetrofitResponse<Api>.map(
    mapSuccess: (Api) -> Response<Domain>,
    mapFailure: ((RetrofitResponse<Api>) -> Response<Domain>)? = null
): Response<Domain> {
    val body = body()

    return if (body != null)
        mapSuccess(body)
    else
        mapFailure?.invoke(this) ?: Response.Failure(toBaseError())
}

private fun toBaseError() = ServerExceptionApiModel(SERVER_EXCEPTION_MESSAGE)
