package com.example.domain.utils


interface LocalUseCaseNonParam<Type>  {
    suspend fun execute() : Type
}

interface LocalUseCaseParamsNotReturn<Params> {
    suspend fun execute(params: Params)
}

interface LocalUseCaseParamsHasReturn<Params, R> {
    suspend fun execute(params: Params) : R
}


