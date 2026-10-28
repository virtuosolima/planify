package br.edu.ifpe.planify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servicos")
data class Servico(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val preco: Double
)
