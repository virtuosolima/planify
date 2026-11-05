package br.edu.ifpe.planify

import android.app.Application
import br.edu.ifpe.planify.data.local.PlanifyDatabase
import br.edu.ifpe.planify.data.repository.ClienteRepository
import br.edu.ifpe.planify.data.repository.CompromissoRepository
import br.edu.ifpe.planify.data.repository.LembreteRepository
import br.edu.ifpe.planify.data.repository.ServicoRepository
import br.edu.ifpe.planify.notification.NotificationScheduler

class PlanifyApplication : Application() {
    val database by lazy { PlanifyDatabase.getDatabase(this) }
    val clienteRepository by lazy { ClienteRepository(database.clienteDao()) }
    val servicoRepository by lazy { ServicoRepository(database.servicoDao()) }
    val compromissoRepository by lazy { CompromissoRepository(database.compromissoDao()) }
    val lembreteRepository by lazy { LembreteRepository(database.lembreteDao()) }
    val notificationScheduler by lazy { NotificationScheduler(this) }
}
