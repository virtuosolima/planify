package br.edu.ifpe.planify.data.local.dao

import androidx.room.*
import br.edu.ifpe.planify.model.Servico
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicoDao {
    @Query("SELECT * FROM servicos ORDER BY nome ASC")
    fun getAllServicos(): Flow<List<Servico>>

    @Query("SELECT * FROM servicos WHERE id = :id")
    suspend fun getServicoById(id: Int): Servico?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServico(servico: Servico)

    @Update
    suspend fun updateServico(servico: Servico)

    @Delete
    suspend fun deleteServico(servico: Servico)
}
