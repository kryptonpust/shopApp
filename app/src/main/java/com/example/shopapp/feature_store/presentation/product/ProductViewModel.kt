package com.example.shopapp.feature_store.presentation.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.domain.useCase.product.ProductUseCase
import com.example.shopapp.feature_store.presentation.cart.CartEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
):ViewModel() {


    private val _productState= mutableStateOf(ProductState())
    val productState: State<ProductState> = _productState

    private var getNotesJob: Job? = null
//    private val _eventFlow = MutableSharedFlow<UiEvents>()
//    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    init{
        viewModelScope.launch {
            productUseCase.refreshRemoteDataUseCase()
            getFilteredProducts("")
        }
    }

    fun onEvent(event: Any)
    {
        when(event)
        {
            is ProductEvent.ProductSelected ->{
                _productState.value=productState.value.copy(
                    selectedProduct = event.product
                )

            }
            is ProductEvent.Search ->{
                getFilteredProducts(event.filter_text)
            }
            is CartEvent.UpdateCart->{
                viewModelScope.launch {
                    productUseCase.insertOrUpdateCartUseCase(event.cart)
                }
            }
        }
    }
    fun getCartForId(id:Long): Flow<Cart>
    {
        return productUseCase.getCartByProductIdUseCase(id)
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