package br.edu.ifpe.planify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.edu.ifpe.planify.PlanifyApplication
import br.edu.ifpe.planify.ui.screens.home.HomeScreen
import br.edu.ifpe.planify.ui.screens.clientes.ClienteListScreen
import br.edu.ifpe.planify.ui.screens.clientes.ClienteFormScreen
import br.edu.ifpe.planify.ui.screens.servicos.ServicoListScreen
import br.edu.ifpe.planify.ui.screens.servicos.ServicoFormScreen
import br.edu.ifpe.planify.ui.screens.compromisso.CompromissoFormScreen
import br.edu.ifpe.planify.ui.viewmodel.ClienteViewModel
import br.edu.ifpe.planify.ui.viewmodel.ClienteViewModelFactory
import br.edu.ifpe.planify.ui.viewmodel.CompromissoViewModel
import br.edu.ifpe.planify.ui.viewmodel.CompromissoViewModelFactory
import br.edu.ifpe.planify.ui.viewmodel.ServicoViewModel
import br.edu.ifpe.planify.ui.viewmodel.ServicoViewModelFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as PlanifyApplication

    val clienteViewModel: ClienteViewModel = viewModel(
        factory = ClienteViewModelFactory(application.clienteRepository)
    )
    val servicoViewModel: ServicoViewModel = viewModel(
        factory = ServicoViewModelFactory(application.servicoRepository)
    )
    val compromissoViewModel: CompromissoViewModel = viewModel(
        factory = CompromissoViewModelFactory(
            application.compromissoRepository,
            application.notificationScheduler
        )
    )

    NavHost(
        navController = navController,
        startDestination = NavTarget.Home.route
    ) {
        composable(NavTarget.Home.route) {
            HomeScreen(
                compromissoViewModel = compromissoViewModel,
                clienteViewModel = clienteViewModel,
                servicoViewModel = servicoViewModel,
                onNavigateToNovoCompromisso = { navController.navigate(NavTarget.NovoCompromisso.route) },
                onNavigateToClientes = { navController.navigate(NavTarget.Clientes.route) },
                onNavigateToServicos = { navController.navigate(NavTarget.Servicos.route) }
            )
        }

        composable(NavTarget.Clientes.route) {
            ClienteListScreen(
                viewModel = clienteViewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddCliente = { navController.navigate(NavTarget.AddCliente.route) },
                onNavigateToEditCliente = { id -> 
                    navController.navigate(NavTarget.EditarCliente.createRoute(id)) 
                }
            )
        }

        composable(NavTarget.AddCliente.route) {
            ClienteFormScreen(
                viewModel = clienteViewModel,
                onNavigateBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }

        composable(
            route = NavTarget.EditarCliente.route,
            arguments = listOf(navArgument("clienteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("clienteId")
            ClienteFormScreen(
                viewModel = clienteViewModel,
                clienteId = id,
                onNavigateBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }

        composable(NavTarget.Servicos.route) {
            ServicoListScreen(
                viewModel = servicoViewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddServico = { navController.navigate(NavTarget.AddServico.route) },
                onNavigateToEditServico = { id -> 
                    navController.navigate(NavTarget.EditarServico.createRoute(id)) 
                }
            )
        }

        composable(NavTarget.AddServico.route) {
            ServicoFormScreen(
                viewModel = servicoViewModel,
                onNavigateBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }

        composable(
            route = NavTarget.EditarServico.route,
            arguments = listOf(navArgument("servicoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("servicoId")
            ServicoFormScreen(
                viewModel = servicoViewModel,
                servicoId = id,
                onNavigateBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }

        composable(NavTarget.NovoCompromisso.route) {
            CompromissoFormScreen(
                compromissoViewModel = compromissoViewModel,
                clienteViewModel = clienteViewModel,
                servicoViewModel = servicoViewModel,
                onNavigateBack = { navController.popBackStack() },
                onSave = { navController.popBackStack() }
            )
        }
    }
}
