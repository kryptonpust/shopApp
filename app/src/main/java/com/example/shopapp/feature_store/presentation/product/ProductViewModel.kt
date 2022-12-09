package com.example.shopapp.feature_store.presentation.product

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.feature_store.domain.useCase.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val test:String?
):ViewModel() {


    private val _productState= mutableStateOf(ProductState())
    val productState: State<ProductState> = _productState

    private var getNotesJob: Job? = null
//    private val _eventFlow = MutableSharedFlow<UiEvents>()
//    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    init{
        if (test != null) {
            Log.d(ProductViewModel::class.simpleName,test)
        }

        viewModelScope.launch {
            productUseCase.refreshRemoteDataUseCase()
            getFilteredProducts("")
        }
    }


    private fun getFilteredProducts(filter_text:String) {
        getNotesJob?.cancel()
        getNotesJob = productUseCase.getProductsUseCase(filter_text).onEach {
            _productState.value=productState.value.copy(
                products = it
            )
        }.launchIn(viewModelScope)
    }
}