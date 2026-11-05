package br.edu.ifpe.planify.data.repository

import br.edu.ifpe.planify.data.local.dao.CompromissoDao
import br.edu.ifpe.planify.model.Compromisso
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class CompromissoRepository(private val compromissoDao: CompromissoDao) {
    val allCompromissos: Flow<List<Compromisso>> = compromissoDao.getAllCompromissos()

    fun getCompromissosByDate(date: LocalDate): Flow<List<Compromisso>> = 
        compromissoDao.getCompromissosByDate(date)

    suspend fun getCompromissoById(id: Int): Compromisso? = 
        compromissoDao.getCompromissoById(id)

    suspend fun insert(compromisso: Compromisso): Long = 
        compromissoDao.insertCompromisso(compromisso)

    suspend fun update(compromisso: Compromisso) = 
        compromissoDao.updateCompromisso(compromisso)

    suspend fun delete(compromisso: Compromisso) = 
        compromissoDao.deleteCompromisso(compromisso)
}
