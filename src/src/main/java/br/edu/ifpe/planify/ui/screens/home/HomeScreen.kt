package br.edu.ifpe.planify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifpe.planify.ui.components.SummaryCard
import br.edu.ifpe.planify.ui.theme.PrimaryBlue
import br.edu.ifpe.planify.ui.theme.TextSecondary
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToNovoCompromisso: () -> Unit,
    onNavigateToClientes: () -> Unit,
    onNavigateToServicos: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planify", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToNovoCompromisso,
                containerColor = PrimaryBlue,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Novo Compromisso")
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentColor = PrimaryBlue
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = onNavigateToClientes) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Person, contentDescription = "Clientes")
                            Text("Clientes", fontSize = 10.sp)
                        }
                    }
                    IconButton(onClick = onNavigateToServicos) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Serviços")
                            Text("Serviços", fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
        ) {
            // Summary Section
            SummarySection()

            Spacer(modifier = Modifier.height(16.dp))

            // Date Picker Section
            DatePickerSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Appointments List
            Text(
                text = "Compromissos do dia",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppointmentsList(
    onNavigateToNovoCompromisso = onNavigateToNovoCompromisso
)
        }
    }
}

@Composable
fun SummarySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryBlue)
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SummaryCard(title = "Total", value = "R$ 1.250,00", modifier = Modifier.weight(1f))
            SummaryCard(title = "Atendimentos", value = "24", modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            SummaryCard(title = "Clientes", value = "18", modifier = Modifier.weight(1f))
            SummaryCard(title = "Média", value = "R$ 52,08", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DatePickerSection() {
    val days = (0..14).map { LocalDate.now().plusDays(it.toLong()) }
    
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(days) { date ->
            val isSelected = date == LocalDate.now()
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) PrimaryBlue else Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.width(60.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("pt", "BR")),
                        color = if (isSelected) Color.White else TextSecondary,
                        fontSize = 12.sp
                    )
                    Text(
                        text = date.dayOfMonth.toString(),
                        color = if (isSelected) Color.White else PrimaryBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun AppointmentsList(
    onNavigateToNovoCompromisso: () -> Unit
) {
    val mockAppointments = listOf(
        MockAppointment("09:00", "Ana Silva", "Corte de Cabelo", "R$ 50,00"),
        MockAppointment("10:30", "Pedro Santos", "Barba", "R$ 30,00"),
        MockAppointment("14:00", "Maria Oliveira", "Coloração", "R$ 120,00")
    )

    if (mockAppointments.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Nenhum compromisso registrado para este dia.",
                color = TextSecondary,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onNavigateToNovoCompromisso) {
                Text("Cadastrar primeiro compromisso")
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mockAppointments) { appointment ->
                AppointmentItem(appointment)
            }
        }
    }
}

@Composable
fun AppointmentItem(appointment: MockAppointment) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = appointment.time, fontWeight = FontWeight.Bold, color = PrimaryBlue)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = appointment.clientName, fontWeight = FontWeight.Bold)
                Text(text = appointment.serviceName, color = TextSecondary, fontSize = 14.sp)
            }
            Text(text = appointment.value, fontWeight = FontWeight.Bold, color = PrimaryBlue)
        }
    }
}

data class MockAppointment(
    val time: String,
    val clientName: String,
    val serviceName: String,
    val value: String
)
