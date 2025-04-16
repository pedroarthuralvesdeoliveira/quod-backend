package br.com.fiap.quodbackend.model

data class ValidationResult(
    val transactionId: String,
    val type: String,
    val isValid: Boolean,
    val fraudType: String? = null,
    val score: Int? = null
)