package com.example.testetecnico.commons

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Results<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Results<T> = Results(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Results<T> =
            Results(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Results<T> = Results(status = Status.LOADING, data = data, message = null)
    }
}