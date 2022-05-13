package me.dio.bankline_m.domain

data class Movimentacao(
    val id : Int,
    val dataHora: String,
    val descricao: String,
    val valor : Double,
    val tipo : TipoMovimentcao,
    val idCorrentista: Int

)
