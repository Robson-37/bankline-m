package me.dio.bankline_m.ui.statement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.dio.bankline_m.data.remote.BanklineRepository

class BankStatementViewModel : ViewModel (){
    fun findBankStatement(accountHolderId: Int) =
        BanklineRepository.findBankStatement(accountHolderId)

}