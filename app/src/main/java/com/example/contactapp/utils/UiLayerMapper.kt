package com.example.contactapp.utils

interface UiLayerMapper<T,E> {
    abstract fun mapDataFromDomainToUI(type:T): E
    abstract fun mapDataFromUIToDomain(type:E): T
}

