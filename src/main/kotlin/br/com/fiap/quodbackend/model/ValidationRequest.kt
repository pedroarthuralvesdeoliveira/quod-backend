package br.com.fiap.quodbackend.model

import java.util.*

data class ValidationRequest(
    val transactionId: String = UUID.randomUUID().toString(),
    val type: String, // "facial", "digital", "document"
    val image: ByteArray,
    val metadata: ImageMetadata
)

data class ImageMetadata(
    val captureDate: String, // ISO 8601
    val latitude: Double?,
    val longitude: Double?,
    val ipAddress: String?,
    val device: DeviceInfo
)

data class DeviceInfo(
    val manufacturer: String,
    val model: String,
    val os: String
)