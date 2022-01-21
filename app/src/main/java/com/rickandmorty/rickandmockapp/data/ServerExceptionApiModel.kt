package com.rickandmorty.rickandmockapp.data

import com.rickandmorty.rickandmockapp.utils.SERVER_EXCEPTION_MESSAGE

data class ServerExceptionApiModel(
    override val message: String? = SERVER_EXCEPTION_MESSAGE
) : Exception(message)
