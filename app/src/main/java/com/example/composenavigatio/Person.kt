package com.example.composenavigatio

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Person(
    val name: String,
    val age: Int,
    val id: Int
): Parcelable
