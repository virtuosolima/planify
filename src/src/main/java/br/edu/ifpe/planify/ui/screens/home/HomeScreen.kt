package br.edu.ifpe.planify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifpe.planify.R
import br.edu.ifpe.planify.model.Cliente
import br.edu.ifpe.planify.model.Compromisso
import br.edu.ifpe.planify.ui.components.SummaryCard
import br.edu.ifpe.planify.ui.state.UiState
import br.edu.ifpe.planify.ui.theme.PrimaryBlue
import br.edu.ifpe.planify.ui.theme.TextSecondary
import br.edu.ifpe.planify.ui.viewmodel.ClienteViewModel
import br.edu.ifpe.planify.ui.viewmodel.CompromissoViewModel
import br.edu.ifpe.planify.ui.viewmodel.ServicoViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

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
    val compromissos by compromissoViewModel.compromissosForDate.collectAsState()
    val allCompromissos by compromissoViewModel.allCompromissos.collectAsState()
    val allClientes by clienteViewModel.allClientes.collectAsState()
    val selectedDate by compromissoViewModel.selectedDate.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var hasLoadedOnce by remember { mutableStateOf(false) }
    LaunchedEffect(allCompromissos, allClientes, compromissos) {
        if (!hasLoadedOnce) {
            hasLoadedOnce = true
        }
    }

    val homeUiState: UiState = when {
        !hasLoadedOnce -> UiState.Carregando
        allCompromissos.isEmpty() && allClientes.isEmpty() && compromissos.isEmpty() -> UiState.Vazio
        else -> UiState.Sucesso(
            HomeScreenData(
                totalCompromissos = allCompromissos.size,
                totalClientes = allClientes.size,
                valorTotal = allCompromissos.sumOf { it.valor },
                compromissos = compromissos,
                selectedDate = selectedDate
            )
        )
    }

    when (homeUiState) {
        UiState.Carregando -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = PrimaryBlue)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.home_loading),
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        UiState.Vazio -> {
            HomeScreenContent(
                compromissos = compromissos,
                allCompromissos = allCompromissos,
                allClientes = allClientes,
                selectedDate = selectedDate,
                snackbarHostState = snackbarHostState,
                onNavigateToNovoCompromisso = onNavigateToNovoCompromisso,
                onNavigateToClientes = onNavigateToClientes,
                onNavigateToServicos = onNavigateToServicos,
                onDeleteCompromisso = { compromisso ->
                    scope.launch {
                        compromissoViewModel.delete(compromisso)
                        val result = snackbarHostState.showSnackbar(
                            message = "Compromisso removido",
                            actionLabel = "Desfazer",
                            duration = SnackbarDuration.Short
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            compromissoViewModel.insert(compromisso)
                        }
                    }
                },
                onDateSelected = { compromissoViewModel.selectDate(it) }
            )
        }
        is UiState.Erro -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = homeUiState.mensagem,
                    color = TextSecondary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        is UiState.Sucesso<*> -> {
            HomeScreenContent(
                compromissos = compromissos,
                allCompromissos = allCompromissos,
                allClientes = allClientes,
                selectedDate = selectedDate,
                snackbarHostState = snackbarHostState,
                onNavigateToNovoCompromisso = onNavigateToNovoCompromisso,
                onNavigateToClientes = onNavigateToClientes,
                onNavigateToServicos = onNavigateToServicos,
                onDeleteCompromisso = { compromisso ->
                    scope.launch {
                        compromissoViewModel.delete(compromisso)
                        val result = snackbarHostState.showSnackbar(
                            message = "Compromisso removido",
                            actionLabel = "Desfazer",
                            duration = SnackbarDuration.Short
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            compromissoViewModel.insert(compromisso)
                        }
                    }
                },
                onDateSelected = { compromissoViewModel.selectDate(it) }
            )
        }
    }
}

private data class HomeScreenData(
    val totalCompromissos: Int,
    val totalClientes: Int,
    val valorTotal: Double,
    val compromissos: List<Compromisso>,
    val selectedDate: LocalDate
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    compromissos: List<Compromisso>,
    allCompromissos: List<Compromisso>,
    allClientes: List<Cliente>,
    selectedDate: LocalDate,
    snackbarHostState: SnackbarHostState,
    onNavigateToNovoCompromisso: () -> Unit,
    onNavigateToClientes: () -> Unit,
    onNavigateToServicos: () -> Unit,
    onDeleteCompromisso: (Compromisso) -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
            SummarySection(
                totalCompromissos = allCompromissos.size,
                totalClientes = allClientes.size,
                valorTotal = allCompromissos.sumOf { it.valor }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DatePickerSection(
                selectedDate = selectedDate,
                onDateSelected = onDateSelected
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Compromissos do dia",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            AppointmentsList(
                compromissos = compromissos,
                onDelete = onDeleteCompromisso
            )
        }
    }
}

@Composable
fun SummarySection(
    totalCompromissos: Int,
    totalClientes: Int,
    valorTotal: Double
) {
    val media = if (totalCompromissos > 0) valorTotal / totalCompromissos else 0.0
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryBlue)
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SummaryCard(title = "Total", value = "R$ %.2f".format(valorTotal), modifier = Modifier.weight(1f))
            SummaryCard(title = "Atendimentos", value = totalCompromissos.toString(), modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            SummaryCard(title = "Clientes", value = totalClientes.toString(), modifier = Modifier.weight(1f))
            SummaryCard(title = "Média", value = "R$ %.2f".format(media), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DatePickerSection(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val days = (-2..12).map { LocalDate.now().plusDays(it.toLong()) }

    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(days) { date ->
            val isSelected = date == selectedDate
            Card(
                onClick = { onDateSelected(date) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentsList(
    compromissos: List<Compromisso>,
    onDelete: (Compromisso) -> Unit
) {
    if (compromissos.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Nenhum compromisso para este dia", color = TextSecondary)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = compromissos,
                key = { it.id }
            ) { appointment ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->
                        if (value == SwipeToDismissBoxValue.EndToStart) {
                            onDelete(appointment)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        val color = when (dismissState.dismissDirection) {
                            SwipeToDismissBoxValue.EndToStart -> Color.Red
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color, RoundedCornerShape(12.dp))
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Excluir",
                                tint = Color.White
                            )
                        }
                    }
                ) {
                    AppointmentItem(appointment)
                }
            }
        }
    }
}

@Composable
fun AppointmentItem(appointment: Compromisso) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
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
                Text(
                    text = appointment.horarioInicial.format(timeFormatter),
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = appointment.descricao, fontWeight = FontWeight.Bold)
                Text(
                    text = if (appointment.temLembrete) "Com lembrete" else "Sem lembrete",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }
            Text(
                text = "R$ %.2f".format(appointment.valor),
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }
    }
}
