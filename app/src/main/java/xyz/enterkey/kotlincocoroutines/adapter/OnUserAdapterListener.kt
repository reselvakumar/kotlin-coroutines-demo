package xyz.enterkey.kotlincocoroutines.adapter

import xyz.enterkey.kotlincocoroutines.models.UserResponseModel

interface OnUserAdapterListener {
    fun onItemClick(data:UserResponseModel)
}