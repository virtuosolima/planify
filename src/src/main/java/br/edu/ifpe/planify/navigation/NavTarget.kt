package br.edu.ifpe.planify.navigation

sealed class NavTarget(val route: String) {
    object Home : NavTarget("home")
    object Clientes : NavTarget("clientes")
    object AddCliente : NavTarget("add_cliente")
    object EditarCliente : NavTarget("editar_cliente/{clienteId}") {
        fun createRoute(clienteId: Int) = "editar_cliente/$clienteId"
    }
    object Servicos : NavTarget("servicos")
    object AddServico : NavTarget("add_servico")
    object EditarServico : NavTarget("editar_servico/{servicoId}") {
        fun createRoute(servicoId: Int) = "editar_servico/$servicoId"
    }
    object NovoCompromisso : NavTarget("novo_compromisso")
}
