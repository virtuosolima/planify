package br.edu.ifpe.planify.ui.screens.compromisso

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.edu.ifpe.planify.ui.components.PlanifyButton
import br.edu.ifpe.planify.ui.components.PlanifyTextField
import br.edu.ifpe.planify.ui.theme.PrimaryBlue
import br.edu.ifpe.planify.ui.theme.TextSecondary

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.edu.ifpe.planify.model.Cliente
import br.edu.ifpe.planify.model.Compromisso
import br.edu.ifpe.planify.model.PaymentStatus
import br.edu.ifpe.planify.model.Servico
import br.edu.ifpe.planify.ui.viewmodel.ClienteViewModel
import br.edu.ifpe.planify.ui.viewmodel.CompromissoViewModel
import br.edu.ifpe.planify.ui.viewmodel.ServicoViewModel
import br.edu.ifpe.planify.ui.components.PlanifyButton
import br.edu.ifpe.planify.ui.components.PlanifyTextField
import br.edu.ifpe.planify.ui.theme.PrimaryBlue
import br.edu.ifpe.planify.ui.theme.TextSecondary
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompromissoFormScreen(
    compromissoViewModel: CompromissoViewModel,
    clienteViewModel: ClienteViewModel,
    servicoViewModel: ServicoViewModel,
    onNavigateBack: () -> Unit,
    onSave: () -> Unit
) {
    val clientes by clienteViewModel.allClientes.collectAsState()
    val servicos by servicoViewModel.allServicos.collectAsState()

    var selectedCliente by remember { mutableStateOf<Cliente?>(null) }
    var selectedServico by remember { mutableStateOf<Servico?>(null) }
    var dataStr by remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) }
    var horarioStr by remember { mutableStateOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))) }
    var valor by remember { mutableStateOf("") }
    var lembreteAtivo by remember { mutableStateOf(false) }

    var expandedCliente by remember { mutableStateOf(false) }
    var expandedServico by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo Agendamento", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cliente Selection
            ExposedDropdownMenuBox(
                expanded = expandedCliente,
                onExpandedChange = { expandedCliente = !expandedCliente }
            ) {
                PlanifyTextField(
                    value = selectedCliente?.nome ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = "Selecionar Cliente",
                    placeholder = "Selecione um cliente",
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedCliente,
                    onDismissRequest = { expandedCliente = false }
                ) {
                    clientes.forEach { cliente ->
                        DropdownMenuItem(
                            text = { Text(cliente.nome) },
                            onClick = {
                                selectedCliente = cliente
                                expandedCliente = false
                            }
                        )
                    }
                }
            }

            // Servico Selection
            ExposedDropdownMenuBox(
                expanded = expandedServico,
                onExpandedChange = { expandedServico = !expandedServico }
            ) {
                PlanifyTextField(
                    value = selectedServico?.nome ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = "Selecionar Serviço",
                    placeholder = "Selecione um serviço",
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedServico,
                    onDismissRequest = { expandedServico = false }
                ) {
                    servicos.forEach { servico ->
                        DropdownMenuItem(
                            text = { Text(servico.nome) },
                            onClick = {
                                selectedServico = servico
                                valor = servico.preco.toString()
                                expandedServico = false
                            }
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PlanifyTextField(
                    value = dataStr,
                    onValueChange = { dataStr = it },
                    label = "Data",
                    modifier = Modifier.weight(1f),
                    placeholder = "DD/MM/AAAA"
                )
                PlanifyTextField(
                    value = horarioStr,
                    onValueChange = { horarioStr = it },
                    label = "Horário",
                    modifier = Modifier.weight(1f),
                    placeholder = "HH:mm"
                )
            }

            PlanifyTextField(
                value = valor,
                onValueChange = { valor = it },
                label = "Valor do Atendimento (R$)",
                placeholder = "0,00"
            )

            // Lembrete Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Ativar Lembrete", fontWeight = FontWeight.SemiBold)
                    Text(text = "Notificar antes do atendimento", color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                }
                Switch(
                    checked = lembreteAtivo,
                    onCheckedChange = { lembreteAtivo = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = PrimaryBlue)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            PlanifyButton(
                text = "Confirmar Agendamento",
                enabled = selectedCliente != null && selectedServico != null,
                onClick = {
                    try {
                        val date = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        val time = LocalTime.parse(horarioStr, DateTimeFormatter.ofPattern("HH:mm"))
                        
                        val compromisso = Compromisso(
                            clienteId = selectedCliente?.id ?: 0,
                            servicoId = selectedServico?.id ?: 0,
                            descricao = "${selectedServico?.nome} - ${selectedCliente?.nome}",
                            data = date,
                            horarioInicial = time,
                            valor = valor.toDoubleOrNull() ?: 0.0,
                            statusPagamento = PaymentStatus.PENDENTE,
                            temLembrete = lembreteAtivo
                        )
                        
                        compromissoViewModel.insert(compromisso)
                        onSave()
                    } catch (e: Exception) {
                        // Tratar erro de parsing (idealmente com feedback visual)
                    }
                }
            )
        }
    }
}
