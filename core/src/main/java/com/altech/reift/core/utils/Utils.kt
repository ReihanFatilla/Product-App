package com.altech.reift.core.utils

object Utils {
    fun String.toWordCase(): String{
        return split("_").map {
            it[0].uppercase()+it.lowercase().substring(1, it.length )
        }.joinToString(separator = " ")
    }
}