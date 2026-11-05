package br.edu.ifpe.planify.data.local.dao

import androidx.room.*
import br.edu.ifpe.planify.model.Compromisso
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CompromissoDao {
    @Query("SELECT * FROM compromissos ORDER BY data ASC, horarioInicial ASC")
    fun getAllCompromissos(): Flow<List<Compromisso>>

    @Query("SELECT * FROM compromissos WHERE data = :date ORDER BY horarioInicial ASC")
    fun getCompromissosByDate(date: LocalDate): Flow<List<Compromisso>>

    @Query("SELECT * FROM compromissos WHERE id = :id")
    suspend fun getCompromissoById(id: Int): Compromisso?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompromisso(compromisso: Compromisso): Long

    @Update
    suspend fun updateCompromisso(compromisso: Compromisso)

    @Delete
    suspend fun deleteCompromisso(compromisso: Compromisso)
}
