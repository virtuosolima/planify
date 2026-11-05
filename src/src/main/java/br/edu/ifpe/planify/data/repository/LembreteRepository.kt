package br.edu.ifpe.planify.data.repository

import br.edu.ifpe.planify.data.local.dao.LembreteDao
import br.edu.ifpe.planify.model.Lembrete
import kotlinx.coroutines.flow.Flow

class LembreteRepository(private val lembreteDao: LembreteDao) {
    val allLembretes: Flow<List<Lembrete>> = lembreteDao.getAllLembretes()

    fun getLembretesForCompromisso(compromissoId: Int): Flow<List<Lembrete>> = 
        lembreteDao.getLembretesForCompromisso(compromissoId)

    suspend fun insert(lembrete: Lembrete) = lembreteDao.insertLembrete(lembrete)

    suspend fun update(lembrete: Lembrete) = lembreteDao.updateLembrete(lembrete)

    suspend fun delete(lembrete: Lembrete) = lembreteDao.deleteLembrete(lembrete)
}
