package com.jeffery.lerestaurant.domain

sealed class ResponseState {
    object Success : ResponseState()
    data class Failure(val message: String?): ResponseState()
}