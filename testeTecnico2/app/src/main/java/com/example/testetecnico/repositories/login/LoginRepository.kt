package com.example.testetecnico.repositories.login

import com.example.testetecnico.repositories.entities.login.User

interface LoginRepositoryContract {

    fun login(email: String, password: String): User?
}

class LoginRepository: LoginRepositoryContract {

    override fun login(email: String, password: String): User? {
        if (email == "email@invalido.com") {
            return null
        }
        return User(email)
    }
}