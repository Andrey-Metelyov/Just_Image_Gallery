package com.example.justimagegallery.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlin.math.max

const val MAX_IMAGE_SIZE = 3040

@Parcelize
data class PicsumPhoto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @Json(name = "download_url") val downloadUrl: String,
) : Parcelable {
    fun getAdaptedImageUrl(): String {
        val maxDimension = max(width, height)
        if (maxDimension > MAX_IMAGE_SIZE) {
            val rate = MAX_IMAGE_SIZE.toDouble() / maxDimension
            val h: Int
            val w: Int
            if (width == maxDimension) {
                h = (height * rate).toInt()
                w = MAX_IMAGE_SIZE
            } else {
                h = MAX_IMAGE_SIZE
                w = (width * rate).toInt()
            }
            val newUrl = downloadUrl.substring(
                0, downloadUrl.lastIndexOf('/', downloadUrl.lastIndexOf('/') - 1)
            ) + '/' + w + '/' + h
            System.err.println("newUrl = $newUrl")
            return newUrl
        } else {
            System.err.println("downloadUrl = $downloadUrl")
            return downloadUrl
        }
    }
}
