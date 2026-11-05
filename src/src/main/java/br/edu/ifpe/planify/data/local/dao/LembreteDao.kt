package br.edu.ifpe.planify.data.local.dao

import androidx.room.*
import br.edu.ifpe.planify.model.Lembrete
import kotlinx.coroutines.flow.Flow

@Dao
interface LembreteDao {
    @Query("SELECT * FROM lembretes")
    fun getAllLembretes(): Flow<List<Lembrete>>

    @Query("SELECT * FROM lembretes WHERE compromissoId = :compromissoId")
    fun getLembretesForCompromisso(compromissoId: Int): Flow<List<Lembrete>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLembrete(lembrete: Lembrete)

    @Update
    suspend fun updateLembrete(lembrete: Lembrete)

    @Delete
    suspend fun deleteLembrete(lembrete: Lembrete)
}
