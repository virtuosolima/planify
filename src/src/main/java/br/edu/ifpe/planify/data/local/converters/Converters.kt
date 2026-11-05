package br.edu.ifpe.planify.data.local.converters

import androidx.room.TypeConverter
import br.edu.ifpe.planify.model.PaymentStatus
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    fun fromDate(value: LocalDate?): String? = value?.format(dateFormatter)

    @TypeConverter
    fun toDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it, dateFormatter) }

    @TypeConverter
    fun fromTime(value: LocalTime?): String? = value?.format(timeFormatter)

    @TypeConverter
    fun toTime(value: String?): LocalTime? = value?.let { LocalTime.parse(it, timeFormatter) }

    @TypeConverter
    fun fromPaymentStatus(status: PaymentStatus): String = status.name

    @TypeConverter
    fun toPaymentStatus(value: String): PaymentStatus = PaymentStatus.valueOf(value)
}
