package edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.clientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.ClienteEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.PrioridadEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.ClienteRepository
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.PrioridadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getClientes()

        _uiState.value = UiState(
            clientes = listOf(

            )
        )
    }

    fun save() {
        viewModelScope.launch {
            if (_uiState.value.nombre.isNullOrBlank() ||
                _uiState.value.direccion.isNullOrBlank() ||
                _uiState.value.telefono.isNullOrBlank() ||
                _uiState.value.telefono?.length != 10 ||
                _uiState.value.telefono?.any { !it.isDigit() } == true ||
                _uiState.value.celular.isNullOrBlank() ||
                _uiState.value.celular?.length != 10 ||
                _uiState.value.celular?.any { !it.isDigit() } == true ||
                _uiState.value.RNC.isNullOrBlank() ||
                _uiState.value.RNC?.length != 9 ||
                _uiState.value.RNC?.any { !it.isDigit() } == true ||
                _uiState.value.email.isNullOrBlank() ||
                !_uiState.value.email!!.contains('@')
            ) {
                _uiState.update {
                    it.copy(errorMessage = "Por favor, completa todos los campos correctamente.", guardado = false)
                }
                return@launch
            }

            val existe = _uiState.value.clientes.firstOrNull { it.nombre == _uiState.value.nombre }

            if (existe != null) {
                _uiState.update {
                    it.copy(errorMessage = "Ya existe un Cliente con este Nombre.", guardado = false)
                }
                return@launch
            }

            clienteRepository.save(_uiState.value.toEntity())
            _uiState.update { it.copy(errorMessage = null, guardado = true) }
        }
    }


    fun update() {
        viewModelScope.launch {
            if (_uiState.value.nombre.isNullOrBlank() ||
                _uiState.value.direccion.isNullOrBlank() ||
                _uiState.value.telefono.isNullOrBlank() ||
                _uiState.value.telefono?.length != 10 ||
                _uiState.value.telefono?.any { !it.isDigit() } == true ||
                _uiState.value.celular.isNullOrBlank() ||
                _uiState.value.celular?.length != 10 ||
                _uiState.value.celular?.any { !it.isDigit() } == true ||
                _uiState.value.RNC.isNullOrBlank() ||
                _uiState.value.RNC?.length != 9 ||
                _uiState.value.RNC?.any { !it.isDigit() } == true ||
                !_uiState.value.email!!.contains('@')
            ) {
                _uiState.update {
                    it.copy(errorMessage = "Por favor, completa todos los campos correctamente.", guardado = false)
                }
                return@launch
            }

            if (_uiState.value.clienteId == null || _uiState.value.clienteId!! <= 0) {
                _uiState.update {
                    it.copy(errorMessage = "Ha ocurrido un error al tratar de identificar el cliente.", guardado = false)
                }
                return@launch
            }

            val existe = _uiState.value.clientes.firstOrNull {
                it.nombre == _uiState.value.nombre && it.clienteId != _uiState.value.clienteId
            }

            if (existe != null) {
                _uiState.update {
                    it.copy(errorMessage = "Ya existe un Cliente con este Nombre.", guardado = false)
                }
                return@launch
            }

            // Guardar el cliente con los nuevos valores
            clienteRepository.save(_uiState.value.toEntity())
            _uiState.update { it.copy(errorMessage = null, guardado = true) }
        }
    }


    fun selectedClientte(clienteId: Int) {
        viewModelScope.launch {
            if (clienteId > 0) {
                val cliente = clienteRepository.getCliente(clienteId)
                _uiState.update { currentState ->
                    currentState.copy(
                        clienteId = cliente?.clienteId,
                        nombre = cliente?.nombre ?: "",
                        telefono = cliente?.telefono?.toString() ?: "",
                        celular = cliente?.celular?.toString() ?: "",
                        RNC = cliente?.RNC?.toString() ?: "",
                        email = cliente?.email ?: "",
                        direccion = cliente?.direccion ?: "",
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            clienteRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getClientes() {
        viewModelScope.launch {
            clienteRepository.getClientes().collect { clientes ->
                _uiState.update {
                    it.copy(clientes = clientes)
                }
            }
        }
    }

    fun onNombreChange(nombre: String?) {
        _uiState.update {
            it.copy(nombre = nombre)
        }
    }

    fun onTelefonoChange(telefono: String?) {
        _uiState.update {
            it.copy(telefono = telefono)
        }
    }

    fun onCelularChange(celular: String?) {
        _uiState.update {
            it.copy(celular = celular)
        }
    }

    fun onRNCChange(rnc: String?) {
        _uiState.update {
            it.copy(RNC = rnc)
        }
    }

    fun onEmailChange(email: String?) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun onDireccionChange(direccion: String?) {
        _uiState.update {
            it.copy(direccion = direccion)
        }
    }

}

data class UiState(
    val clienteId: Int? = null,
    val nombre: String? = "",
    val telefono: String? = null,
    val celular: String? = null,
    val RNC: String? = null,
    val email: String? = null,
    val direccion: String? = null,
    val errorMessage: String? = null,
    var guardado: Boolean? = false,
    val clientes: List<ClienteEntity> = emptyList()
)

fun UiState.toEntity() = ClienteEntity(
    clienteId = clienteId ?: 0,
    nombre = nombre ?: "",
    telefono = telefono ?: "",
    celular = celular ?: "",
    RNC = RNC ?: "",
    email = email ?: "",
    direccion = direccion ?: ""
)
