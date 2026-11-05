package br.edu.ifpe.planify.ui.screens.servicos

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
import br.edu.ifpe.planify.model.Servico
import br.edu.ifpe.planify.ui.viewmodel.ServicoViewModel
import br.edu.ifpe.planify.ui.components.PlanifyButton
import br.edu.ifpe.planify.ui.components.PlanifyTextField
import br.edu.ifpe.planify.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicoFormScreen(
    viewModel: ServicoViewModel,
    servicoId: Int? = null,
    onNavigateBack: () -> Unit,
    onSave: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }

    val isEditing = servicoId != null

    LaunchedEffect(servicoId) {
        if (isEditing) {
            viewModel.getServicoById(servicoId!!)?.let { servico ->
                nome = servico.nome
                descricao = servico.descricao
                preco = servico.preco.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = if (isEditing) "Editar Serviço" else "Novo Serviço", 
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
                label = "Nome do Serviço",
                placeholder = "Ex: Corte de Cabelo"
            )

            PlanifyTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = "Descrição",
                placeholder = "O que está incluído no serviço?"
            )

            PlanifyTextField(
                value = preco,
                onValueChange = { preco = it },
                label = "Preço Base (R$)",
                placeholder = "0,00"
            )

            Spacer(modifier = Modifier.weight(1f))

            PlanifyButton(
                text = if (isEditing) "Salvar Alterações" else "Cadastrar Serviço",
                onClick = {
                    val servico = Servico(
                        id = servicoId ?: 0,
                        nome = nome,
                        descricao = descricao,
                        preco = preco.toDoubleOrNull() ?: 0.0
                    )
                    if (isEditing) {
                        viewModel.update(servico)
                    } else {
                        viewModel.insert(servico)
                    }
                    onSave()
                }
            )
        }
    }
}
