package com.example.shopapp.feature_store.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.feature_store.domain.useCase.cart.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
):ViewModel() {

    private val _cartState= mutableStateOf(CartState())
    val cartState: State<CartState> = _cartState


    init {
        viewModelScope.launch {
            cartUseCase.getAllCartsUseCase().collectLatest {
                _cartState.value=cartState.value.copy(
                    productWithCart = it
                )
            }
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
        }
    }
}