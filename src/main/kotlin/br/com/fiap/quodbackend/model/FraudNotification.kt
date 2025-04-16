package br.com.fiap.quodbackend.model

data class FraudNotification(
    val transactionId: String,
    val biometricType: String,
    val fraudType: String,
    val captureDate: String,
    val device: DeviceInfo,
    val notificationChannels: List<String>,
    val notifiedBy: String = "sistema-de-monitoramento",
    val metadata: ImageMetadata
)