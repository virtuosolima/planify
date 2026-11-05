package br.edu.ifpe.planify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifpe.planify.ui.theme.PrimaryBlue
import br.edu.ifpe.planify.ui.theme.TextSecondary
import br.edu.ifpe.planify.model.Compromisso
import br.edu.ifpe.planify.ui.viewmodel.ClienteViewModel
import br.edu.ifpe.planify.ui.viewmodel.CompromissoViewModel
import br.edu.ifpe.planify.ui.viewmodel.HomeUiState
import br.edu.ifpe.planify.ui.viewmodel.ServicoViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    compromissoViewModel: CompromissoViewModel,
    clienteViewModel: ClienteViewModel,
    servicoViewModel: ServicoViewModel,
    onNavigateToNovoCompromisso: () -> Unit,
    onNavigateToClientes: () -> Unit,
    onNavigateToServicos: () -> Unit
) {
    val uiState by compromissoViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Planify", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToNovoCompromisso, containerColor = PrimaryBlue, contentColor = Color.White) {
                Icon(Icons.Default.Add, contentDescription = "Novo")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(Color(0xFFF8F9FA))) {
            
            Box(modifier = Modifier.fillMaxSize()) {
                when (val state = uiState) {
                    is HomeUiState.Carregando -> {
                        Text("Aguardando implementação do Carregando...", Modifier.align(Alignment.Center))
                    }
                    
                    is HomeUiState.Vazio -> {
                        Text("Aguardando implementação do estado Vazio...", Modifier.align(Alignment.Center))
                    }
                    
                    is HomeUiState.Sucesso -> {
                        // IMPLEMENTADO POR THALES (ESTADO DE SUCESSO - UI POLIDA)
                        AppointmentsList(compromissos = state.compromissos)
                    }
                    
                    is HomeUiState.Erro -> {
                        Text("Erro: ${state.mensagem}", Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}

@Composable
fun AppointmentsList(compromissos: List<Compromisso>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = compromissos, key = { it.id }) { appointment ->
            AppointmentItem(appointment)
        }
    }
}

@Composable
fun AppointmentItem(appointment: Compromisso) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Coluna do Horário
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = appointment.horarioInicial.format(timeFormatter),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryBlue
                )
                if (appointment.temLembrete) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            // Divisor Vertical Simbolizado
            Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.LightGray))

            Spacer(modifier = Modifier.width(20.dp))

            // Detalhes do Compromisso
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = appointment.descricao,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Cliente ID: ${appointment.clienteId}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }

            // Valor
            Text(
                text = "R$ %.2f".format(appointment.valor),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }
    }
}
