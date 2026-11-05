package br.edu.ifpe.planify.data.repository

import br.edu.ifpe.planify.data.local.dao.ServicoDao
import br.edu.ifpe.planify.model.Servico
import kotlinx.coroutines.flow.Flow

class ServicoRepository(private val servicoDao: ServicoDao) {
    val allServicos: Flow<List<Servico>> = servicoDao.getAllServicos()

    suspend fun getServicoById(id: Int): Servico? = servicoDao.getServicoById(id)

    suspend fun insert(servico: Servico) = servicoDao.insertServico(servico)

    suspend fun update(servico: Servico) = servicoDao.updateServico(servico)

    suspend fun delete(servico: Servico) = servicoDao.deleteServico(servico)
}
