package com.example.shopapp.common.utils

sealed class UIEvents {
    data class SnackBarEvent(val message:String):UIEvents()
    data class NavigateEvent(val route: String,val popUpTo:String?) : UIEvents()
}