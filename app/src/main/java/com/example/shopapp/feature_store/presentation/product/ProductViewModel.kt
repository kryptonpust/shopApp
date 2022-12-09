package com.example.shopapp.feature_store.presentation.product

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.common.utils.Resource
import com.example.shopapp.common.utils.UIEvents
import com.example.shopapp.feature_store.data.entity.Cart
import com.example.shopapp.feature_store.domain.useCase.product.ProductUseCase
import com.example.shopapp.feature_store.presentation.cart.CartEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
):ViewModel() {


    private val _productState= mutableStateOf(ProductState())
    val productState: State<ProductState> = _productState


    private var getLocalJob: Job? = null
    private val _eventFlow = MutableSharedFlow<UIEvents>()
    val eventFlow: SharedFlow<UIEvents> = _eventFlow.asSharedFlow()

    init{
        viewModelScope.launch {
            getFilteredProducts("")
            when(val res=productUseCase.refreshRemoteDataUseCase()){
                is Resource.Success->{
                    _eventFlow.emit(UIEvents.SnackBarEvent("Refresh Success"))
                }
                is Resource.Error->{
                    _eventFlow.emit(UIEvents.SnackBarEvent(res.message ?: "Unknown error occurred!"))
                }
                else -> {}
            }
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
        getLocalJob?.cancel()
        _productState.value=productState.value.copy(
            products = emptyList(),
            isLoading =true
        )
        getLocalJob = productUseCase.getProductsUseCase(filter_text).onEach {
            _productState.value=productState.value.copy(
                products = it,
                isLoading = false
            )
        }.launchIn(viewModelScope)
    }
}