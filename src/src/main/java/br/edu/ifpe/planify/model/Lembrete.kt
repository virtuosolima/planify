package br.edu.ifpe.planify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lembretes")
data class Lembrete(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val compromissoId: Int,
    val antecedenciaMinutos: Int,
    val ativo: Boolean = true
)
