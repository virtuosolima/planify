package br.edu.ifpe.planify.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import br.edu.ifpe.planify.model.Compromisso
import java.time.LocalDateTime
import java.time.ZoneId

class NotificationScheduler(private val context: Context) {

    fun scheduleNotification(compromisso: Compromisso) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("title", "Lembrete de Compromisso")
            putExtra("message", "Você tem um compromisso: ${compromisso.descricao} em breve.")
            putExtra("notificationId", compromisso.id)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            compromisso.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Calcula o horário do lembrete (por padrão 30 minutos antes)
        // No futuro isso pode ser configurável pela entidade Lembrete
        val appointmentDateTime = LocalDateTime.of(compromisso.data, compromisso.horarioInicial)
        val reminderTime = appointmentDateTime.minusMinutes(30)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        if (reminderTime > System.currentTimeMillis()) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                reminderTime,
                pendingIntent
            )
        }
    }

    fun cancelNotification(compromissoId: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            compromissoId,
            intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent)
        }
    }
}
