package br.edu.ifpe.planify.ui.screens.clientes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.edu.ifpe.planify.model.Cliente
import br.edu.ifpe.planify.ui.viewmodel.ClienteViewModel
import br.edu.ifpe.planify.ui.components.PlanifyButton
import br.edu.ifpe.planify.ui.components.PlanifyTextField
import br.edu.ifpe.planify.ui.theme.PrimaryBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteFormScreen(
    viewModel: ClienteViewModel,
    clienteId: Int? = null,
    onNavigateBack: () -> Unit,
    onSave: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }
    
    val scope = rememberCoroutineScope()
    val isEditing = clienteId != null

    LaunchedEffect(clienteId) {
        if (isEditing) {
            viewModel.getClienteById(clienteId!!)?.let { cliente ->
                nome = cliente.nome
                telefone = cliente.telefone
                observacoes = cliente.observacoes ?: ""
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = if (isEditing) "Editar Cliente" else "Novo Cliente", 
                        color = Color.White, 
                        fontWeight = FontWeight.Bold 
                    ) 
                },
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
            PlanifyTextField(
                value = nome,
                onValueChange = { nome = it },
                label = "Nome Completo",
                placeholder = "Digite o nome do cliente"
            )

            PlanifyTextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = "Telefone",
                placeholder = "(81) 98888-7777"
            )

            PlanifyTextField(
                value = observacoes,
                onValueChange = { observacoes = it },
                label = "Observações (Opcional)",
                placeholder = "Ex: Prefere atendimento à tarde"
            )

            Spacer(modifier = Modifier.weight(1f))

            PlanifyButton(
                text = if (isEditing) "Salvar Alterações" else "Cadastrar Cliente",
                onClick = {
                    val cliente = Cliente(
                        id = clienteId ?: 0,
                        nome = nome,
                        telefone = telefone,
                        observacoes = observacoes
                    )
                    if (isEditing) {
                        viewModel.update(cliente)
                    } else {
                        viewModel.insert(cliente)
                    }
                    onSave()
                }
            )
        }
    }
}
