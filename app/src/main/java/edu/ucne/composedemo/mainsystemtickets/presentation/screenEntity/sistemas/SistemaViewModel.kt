package edu.ucne.composedemo.mainsystemtickets.presentation.screenEntity.sistemas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.composedemo.mainsystemtickets.data.local.entities.SistemaEntity
import edu.ucne.composedemo.mainsystemtickets.data.local.repository.SistemaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val sistemaRepository: SistemaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSistema()

        _uiState.value = UiState(
            sistemas = listOf(

            )
        )
    }

    fun save() {
        viewModelScope.launch {

            val existe = _uiState.value.sistemas.firstOrNull { it.nombre == _uiState.value.nombre }

            if (existe != null) {
                _uiState.update {
                    it.copy(errorMessage = "Ya existe un Sistema con ese nombre.", guardado = false)
                }
                return@launch
            }

            sistemaRepository.save(_uiState.value.toEntity())
            _uiState.update { it.copy(errorMessage = null, guardado = true) }
        }
    }

    fun update() {
        viewModelScope.launch {
            if (_uiState.value.nombre.isNullOrBlank()) {
                _uiState.update {
                    it.copy(errorMessage = "Por favor, completa todos los campos correctamente.", guardado = false)
                }
                return@launch
            }

            if (_uiState.value.sistemaId == null || _uiState.value.sistemaId!! <= 0) {
                _uiState.update {
                    it.copy(errorMessage = "Ha ocurrido un error al tratar de identificar el sistema.", guardado = false)
                }
                return@launch
            }

            val existe = _uiState.value.sistemas.firstOrNull {
                it.nombre == _uiState.value.nombre && it.sistemaId != _uiState.value.sistemaId
            }

            if (existe != null) {
                _uiState.update {
                    it.copy(errorMessage = "Ya existe un Sistema con ese Nombre.", guardado = false)
                }
                return@launch
            }

            sistemaRepository.save(_uiState.value.toEntity())
            _uiState.update { it.copy(errorMessage = null, guardado = true) }
        }
    }

    fun selectedSistema(sistemaId: Int) {
        viewModelScope.launch {
            if (sistemaId > 0) {
                val sistema = sistemaRepository.getSistema(sistemaId)
                _uiState.update { currentState ->
                    currentState.copy(
                        sistemaId = sistema?.sistemaId,
                        nombre = sistema?.nombre ?: "",
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            sistemaRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getSistema() {
        viewModelScope.launch {
            sistemaRepository.getSistema().collect { sistemas ->
                _uiState.update {
                    it.copy(sistemas = sistemas)
                }
            }
        }
    }

    fun onNombreChange(nombre: String?) {
        _uiState.update {
            it.copy(nombre = nombre)
        }
    }
}

data class UiState(
    val sistemaId: Int? = null,
    val nombre: String? = "",
    val errorMessage: String? = null,
    var guardado: Boolean? = false,
    val sistemas: List<SistemaEntity> = emptyList()
)

fun UiState.toEntity() = SistemaEntity(
    sistemaId = sistemaId,
    nombre = nombre ?: "",
)