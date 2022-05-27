package com.example.testetecnico.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testetecnico.commons.extensions.checkEmail
import com.example.testetecnico.repositories.login.LoginRepositoryContract

class LoginViewModel(private val loginRepository: LoginRepositoryContract) : ViewModel() {

    val messageError = MutableLiveData<String>()
    val isLoginSuccess = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        if (verifyFields(email, password)) {
            val login = loginRepository.login(email, password)
            if (login != null) {
                isLoginSuccess.postValue(true)
            } else {
                messageError.postValue("E-mail ou senha não confere")
            }
        }
    }

    private fun verifyFields(email: String, password: String): Boolean {
        if (email.isEmpty()){
            messageError.postValue("Campo E-mail está vazio, preencha para fazer o login.")
            return false
        } else if (!email.checkEmail()) {
            messageError.postValue("Insira um E-mail válido")
            return false
        } else if (password.isEmpty()) {
            messageError.postValue("Campo Senha está vazio, preencha para fazer o login.")
            return false
        } else if (password.length < 6) {
            messageError.postValue("A senha deve ter mais do que seis caracteres")
            return false
        }
        return true
    }
}