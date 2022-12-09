package com.example.shopapp.feature_store.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.feature_store.domain.useCase.cart.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
):ViewModel() {

    private val _cartState= mutableStateOf(CartState())
    val cartState: State<CartState> = _cartState
    private var getLocalJob: Job? = null

    init {
        viewModelScope.launch {
            getFilteredCarts("")
        }
    }


    fun onEvent(event: CartEvent){
        when(event)
        {
            is CartEvent.UpdateCart ->{
                viewModelScope.launch {
                    cartUseCase.insertOrUpdateCartUseCase(event.cart)
                }
            }
            is CartEvent.DeleteCart->{
                viewModelScope.launch {
                    cartUseCase.deleteCartByIdUseCase(event.id)
                }
            }
            is CartEvent.Search->{
                getFilteredCarts(event.filter_text)
            }
        }
    }

    private fun getFilteredCarts(filter_text:String) {
        getLocalJob?.cancel()
        getLocalJob = cartUseCase.getAllCartsUseCase(filter_text).onEach {
            _cartState.value=cartState.value.copy(
                productWithCart = it
            )
        }.launchIn(viewModelScope)
    }
}