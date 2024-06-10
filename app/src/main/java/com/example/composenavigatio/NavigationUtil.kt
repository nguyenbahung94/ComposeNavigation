package com.example.composenavigatio

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

object NavigationUtil {

   inline fun <reified T: Parcelable> parcelableNavType(): NavType<T> {
        return object: NavType<T>(isNullableAllowed = true) {
            override fun get(bundle: Bundle, key: String): T? {
                return bundle.getParcelable(key) as? T
            }

            override fun parseValue(value: String): T {
               return Json{ignoreUnknownKeys = true}.decodeFromString(Uri.decode(value))
            }

            override fun put(bundle: Bundle, key: String, value: T) {
               bundle.putParcelable(key, value)
            }
        }
    }
}