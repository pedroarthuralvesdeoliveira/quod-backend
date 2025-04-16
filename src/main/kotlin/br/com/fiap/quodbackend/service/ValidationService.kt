package br.com.fiap.quodbackend.service

import br.com.fiap.quodbackend.model.FraudNotification
import br.com.fiap.quodbackend.model.ImageMetadata
import br.com.fiap.quodbackend.model.ValidationRequest
import br.com.fiap.quodbackend.model.ValidationResult
import br.com.fiap.quodbackend.repository.ValidationRepository
import org.opencv.core.Mat
import org.opencv.imgcodecs.Imgcodecs
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ValidationService(
    private val validationRepository: ValidationRepository,
    private val notificationService: NotificationService
) {
    fun validateBiometric(request: ValidationRequest): ValidationResult {
        // Basic image validation
        val isImageValid = validateImage(request.image, request.metadata)
        val fraudType = detectFraud(request.image, request.type)

        val result = ValidationResult(
            transactionId = request.transactionId,
            type = request.type,
            isValid = isImageValid && fraudType == null,
            fraudType = fraudType,
            score = if (fraudType == null) calculateScore() else null
        )

        // Persist result
        validationRepository.save(result)

        // Notify if fraud detected
        if (fraudType != null) {
            notificationService.notifyFraud(
                FraudNotification(
                    transactionId = request.transactionId,
                    biometricType = request.type,
                    fraudType = fraudType,
                    captureDate = request.metadata.captureDate,
                    device = request.metadata.device,
                    notificationChannels = listOf("sms", "email"),
                    metadata = request.metadata
                )
            )
        }

        return result
    }

    fun validateDocument(request: ValidationRequest): ValidationResult {
        // Similar logic for document validation
        val isImageValid = validateImage(request.image, request.metadata)
        val fraudType = detectDocumentFraud(request.image)

        val result = ValidationResult(
            transactionId = request.transactionId,
            type = "document",
            isValid = isImageValid && fraudType == null,
            fraudType = fraudType
        )

        validationRepository.save(result)

        if (fraudType != null) {
            notificationService.notifyFraud(
                FraudNotification(
                    transactionId = request.transactionId,
                    biometricType = "document",
                    fraudType = fraudType,
                    captureDate = request.metadata.captureDate,
                    device = request.metadata.device,
                    notificationChannels = listOf("sms", "email"),
                    metadata = request.metadata
                )
            )
        }

        return result
    }

    private fun validateImage(image: ByteArray, metadata: ImageMetadata): Boolean {
        // Check format, size, quality
        val mat = Imgcodecs.imdecode(Mat(), image.size)
        if (mat.empty()) return false

        // Validate metadata
        val captureDate = LocalDateTime.parse(metadata.captureDate, DateTimeFormatter.ISO_DATE_TIME)
        if (captureDate.isAfter(LocalDateTime.now())) return false

        return true
    }

    private fun detectFraud(image: ByteArray, type: String): String? {
        // Mock fraud detection (replace with real ML model)
        return if (Math.random() > 0.9) "deepfake" else null
    }

    private fun detectDocumentFraud(image: ByteArray): String? {
        // Mock document fraud detection
        return if (Math.random() > 0.9) "photo_of_photo" else null
    }

    private fun calculateScore(): Int {
        // Mock score calculation (300-1000)
        return (300 + (Math.random() * 700)).toInt()
    }
}