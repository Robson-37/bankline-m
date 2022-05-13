package me.dio.bankline_m.data.remote

import me.dio.bankline_m.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineApi {
    @GET(value = "movimentacao/{id}")
  suspend fun findBankStatement(@Path(value = "id") accountHolderId: Int): List<Movimentacao>
}