package me.dio.bankline_m.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import me.dio.bankline_m.R
import me.dio.bankline_m.data.remote.State
import me.dio.bankline_m.databinding.ActivityBankStatementBinding
import me.dio.bankline_m.databinding.ActivityWelcomeBinding
import me.dio.bankline_m.domain.Correntista
import me.dio.bankline_m.domain.Movimentacao
import me.dio.bankline_m.domain.TipoMovimentcao
import java.util.ArrayList

class BankStatementActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_ACCOUNT_HOLDER = "me.dio.bankline_m.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw  IllegalAccessException()
    }
    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()
    }

    private fun findBankStatement() {
       viewModel.findBankStatement(accountHolder.id).observe(this){ state ->
           when(state){
               is State.Success ->{
                   binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                   binding.srlBankStatement.isRefreshing = false
               }
               is State.Error -> {
                   state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                   binding.srlBankStatement.isRefreshing = false
               }
               State.Wait ->  binding.srlBankStatement.isRefreshing = true
           }

       }
    }
}