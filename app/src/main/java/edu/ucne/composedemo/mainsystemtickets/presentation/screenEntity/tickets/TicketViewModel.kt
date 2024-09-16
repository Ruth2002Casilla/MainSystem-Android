package edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.ClienteEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.TicketEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.ClienteRepository
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.PrioridadRepository
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.SistemaRepository
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository,
    private val prioridadRepository: PrioridadRepository,
    private val sistemaRepository: SistemaRepository,
    private val clienteRepository: ClienteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTickets()
        getPrioridades()
        getSistemas()
        getClientes()
    }

    fun save() {
        viewModelScope.launch {
            if (_uiState.value.descripcion.isNullOrBlank() ||
                _uiState.value.cliente.isNullOrBlank() ||
                _uiState.value.asunto.isNullOrBlank() ||
                _uiState.value.fecha.isNullOrBlank() ||
                _uiState.value.prioridadId == null ||
                _uiState.value.sistemaId == null ||
                _uiState.value.clienteId == null) {
                _uiState.update {
                    it.copy(errorMessage = "Por favor, completa todos los campos correctamente.", guardado = false)
                }
                return@launch
            }

            val ticket = _uiState.value.toEntity()
            ticketRepository.save(ticket)
            _uiState.update { it.copy(errorMessage = null, guardado = true) }
        }
    }

    fun update() {
        viewModelScope.launch {
            if (_uiState.value.descripcion.isNullOrBlank() ||
                _uiState.value.cliente.isNullOrBlank() ||
                _uiState.value.asunto.isNullOrBlank() ||
                _uiState.value.fecha.isNullOrBlank() ||
                _uiState.value.prioridadId == null ||
                _uiState.value.ticketId == null ||
                _uiState.value.sistemaId == null ||
                _uiState.value.clienteId == null) {
                _uiState.update {
                    it.copy(errorMessage = "Por favor, completa todos los campos correctamente.", guardado = false)
                }
                return@launch
            }

            val ticket = _uiState.value.toEntity()
            ticketRepository.save(ticket)
            _uiState.update { it.copy(errorMessage = null, guardado = true) }
        }
    }

    fun selectedTicket(ticketId: Int) {
        viewModelScope.launch {
            if (ticketId > 0) {
                val ticket = ticketRepository.getTicket(ticketId)
                _uiState.update { currentState ->
                    currentState.copy(
                        ticketId = ticket?.TicketId,
                        prioridadId = ticket?.PrioridadId,
                        sistemaId = ticket?.SistemaId,
                        clienteId = ticket?.ClienteId,
                        fecha = ticket?.Fecha ?: "",
                        cliente = uiState.value.clientes.find { it.clienteId == ticket?.ClienteId }?.nombre ?: "",
                        asunto = ticket?.Asunto ?: "",
                        descripcion = ticket?.Descripcion ?: "",
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            if (_uiState.value.ticketId != null) {
                ticketRepository.delete(_uiState.value.toEntity())
                _uiState.update { it.copy(ticketId = null) }
            }
        }
    }

    private fun getTickets() {
        viewModelScope.launch {
            ticketRepository.getTicket().collect { tickets ->
                _uiState.update { it.copy(tickets = tickets) }
            }
        }
    }

    private fun getPrioridades() {
        viewModelScope.launch {
            prioridadRepository.getPrioridades().collect { prioridades ->
                _uiState.update { it.copy(prioridades = prioridades) }
            }
        }
    }

    private fun getSistemas() {
        viewModelScope.launch {
            sistemaRepository.getSistema().collect { sistemas ->
                _uiState.update { it.copy(sistemas = sistemas) }
            }
        }
    }

    private fun getClientes() {
        viewModelScope.launch {
            clienteRepository.getClientes().collect { clientes ->
                _uiState.update { it.copy(clientes = clientes) }
            }
        }
    }


    fun onSistemaSiChange(sistemaId: Int?) {
        _uiState.update {
            it.copy(sistemaId = sistemaId)
        }
    }


    fun onDescripcionChange(descripcion: String?) {
        _uiState.update {
            it.copy(descripcion = descripcion)
        }
    }

    fun onSistemaChange(sistemaId: Int) {
        _uiState.update {
            it.copy(sistemaId = sistemaId)
        }
    }

    fun onClienteChange(cliente: String?) {
        _uiState.update {
            it.copy(cliente = cliente)
        }
    }

    fun onSistemaObChange(sistema: String?) {
        _uiState.update {
            it.copy(sistema = sistema)
        }
    }

    fun findSistemaIdByName(sistemaNombre: String?): Int? {
        return _uiState.value.sistemas.find { it.nombre == sistemaNombre }?.sistemaId
    }


    fun onClienteChange(clienteId: Int) {
        _uiState.update {
            it.copy(clienteId = clienteId)
        }
    }


    fun onAsuntoChange(asunto: String?) {
        _uiState.update {
            it.copy(asunto = asunto)
        }
    }

    fun onPrioridadIdChange(prioridadId: Int?) {
        _uiState.update {
            it.copy(prioridadId = prioridadId)
        }
    }

    fun onSistemaIdChange(sistemaId: Int?) {
        _uiState.update {
            it.copy(sistemaId = sistemaId)
        }
    }

    fun onClienteIdChange(clienteId: Int?) {
        _uiState.update {
            it.copy(clienteId = clienteId)
        }
    }

    fun onFechaChange(fecha: String?) {
        _uiState.update {
            it.copy(fecha = fecha)
        }
    }
}

data class UiState(
    val ticketId: Int? = null,
    val prioridadId: Int? = null,
    val sistemaId: Int? = null,
    val sistema: String? = null,
    val clienteId: Int? = null,
    val fecha: String? = "",
    val cliente: String? = null,
    val asunto: String? = null,
    val descripcion: String? = "",
    val errorMessage: String? = null,
    var guardado: Boolean? = false,
    val tickets: List<TicketEntity> = emptyList(),
    val prioridades: List<PrioridadEntity> = emptyList(),
    val sistemas: List<SistemaEntity> = emptyList(),
    val clientes: List<ClienteEntity> = emptyList()
)

fun UiState.toEntity() = TicketEntity(
    TicketId = ticketId,
    PrioridadId = prioridadId,
    SistemaId = sistemaId,
    ClienteId = clienteId,
    Fecha = fecha ?: "",
    Asunto = asunto ?: "",
    Descripcion = descripcion ?: ""
)
