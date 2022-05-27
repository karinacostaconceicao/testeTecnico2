package com.example.testetecnico.commons.extensions

import java.util.regex.Pattern

fun String.checkEmail(): Boolean {
    return Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
            .matcher(this).matches()
}