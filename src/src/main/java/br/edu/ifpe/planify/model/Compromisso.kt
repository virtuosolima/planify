package br.edu.ifpe.planify.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "compromissos")
data class Compromisso(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clienteId: Int, // FK para Cliente
    val servicoId: Int, // FK para Servico
    val descricao: String,
    val data: LocalDate,
    val horarioInicial: LocalTime,
    val horarioFinal: LocalTime? = null,
    val valor: Double,
    val statusPagamento: PaymentStatus = PaymentStatus.PENDENTE,
    val observacoes: String? = null,
    val temLembrete: Boolean = false
)
