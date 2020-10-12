package com.noemi.android.core.domain

import java.io.Serializable

data class Model(
    val id: Int,
    val title: String,
    val photoUrl: String
) : Serializable {

    override fun toString(): String {
        return "Model: id=$id, title='$title', photoUrl='$photoUrl'"
    }
}