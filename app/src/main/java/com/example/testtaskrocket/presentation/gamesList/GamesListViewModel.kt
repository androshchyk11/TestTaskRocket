package com.example.testtaskrocket.presentation.gamesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskrocket.domain.usecases.DefaultGetGamesUseCase
import androidx.lifecycle.viewModelScope
import com.example.testtaskrocket.data.connectionManager.ConnectionManager
import com.example.testtaskrocket.domain.entity.GameEntity
import com.example.testtaskrocket.domain.result.BaseResult
import com.example.testtaskrocket.presentation.viewState.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(
    private val getAccessoriesUseCase: DefaultGetGamesUseCase,
    private val connectionManager: ConnectionManager
) : ViewModel() {
    init {
        getAccessories("")
    }

    private val _accessoriesLiveData = MutableLiveData<ViewState<List<GameEntity>>>()
    val accessoriesLiveData: LiveData<ViewState<List<GameEntity>>> = _accessoriesLiveData

    val searchLiveData = MutableLiveData("")

    fun getAccessories(name: String) {
        if (connectionManager.isConnected.value == false) {
            _accessoriesLiveData.postValue(ViewState.Error(Throwable("No internet connection")))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _accessoriesLiveData.postValue(ViewState.Loading)
            _accessoriesLiveData.postValue(
                when (val result = getAccessoriesUseCase.getGames(name)) {
                    is BaseResult.Success -> ViewState.Success(result.value.games)
                    is BaseResult.Error -> ViewState.Error(result.cause)
                }
            )
        }
    }

    fun changeToDefaultValue() {
        _accessoriesLiveData.postValue(ViewState.Default)
    }
}