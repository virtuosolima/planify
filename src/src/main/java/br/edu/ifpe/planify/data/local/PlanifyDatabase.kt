package br.edu.ifpe.planify.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.edu.ifpe.planify.data.local.converters.Converters
import br.edu.ifpe.planify.data.local.dao.ClienteDao
import br.edu.ifpe.planify.data.local.dao.CompromissoDao
import br.edu.ifpe.planify.data.local.dao.LembreteDao
import br.edu.ifpe.planify.data.local.dao.ServicoDao
import br.edu.ifpe.planify.model.Cliente
import br.edu.ifpe.planify.model.Compromisso
import br.edu.ifpe.planify.model.Lembrete
import br.edu.ifpe.planify.model.Servico

@Database(
    entities = [Cliente::class, Servico::class, Compromisso::class, Lembrete::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PlanifyDatabase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao
    abstract fun servicoDao(): ServicoDao
    abstract fun compromissoDao(): CompromissoDao
    abstract fun lembreteDao(): LembreteDao

    companion object {
        @Volatile
        private var INSTANCE: PlanifyDatabase? = null

        fun getDatabase(context: Context): PlanifyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlanifyDatabase::class.java,
                    "planify_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
