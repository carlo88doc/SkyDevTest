package it.carlo.skydevtest.utils

import it.carlo.skydevtest.R

const val ERROR_UNKNOWN = -1
const val ERROR_RESOURCE_PRIVATE = 0
const val ERROR_RESOURCE_EMPTY = 1

fun getStringResourceFromErrorCode(errorCode:Int):Int{
    return when (errorCode){
        ERROR_RESOURCE_PRIVATE -> R.string.photos_loading_error_private
        ERROR_RESOURCE_EMPTY -> R.string.photos_loading_error_empty
        ERROR_UNKNOWN ->  R.string.photos_loading_error
        else -> R.string.photos_loading_error
    }
}
