package com.example.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DataLayerMapper<T,E> {
    abstract fun mapDataFromDomainToData(type:T): E
    abstract fun mapDataFromDataToDomain(type:E): T
}

fun<T,E> mapListFromDb(result: Flow<List<T>>, mapper: DataLayerMapper<E,T>): Flow<List<E>> {
    return result.map { it ->
        it.map {
            mapper.mapDataFromDataToDomain(it)
        }
    }
}
