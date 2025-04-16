package br.com.fiap.quodbackend.repository

import br.com.fiap.quodbackend.model.ValidationResult
import org.springframework.data.mongodb.repository.MongoRepository

interface ValidationRepository : MongoRepository<ValidationResult, String> {
    fun findByTransactionId(transactionId: String): ValidationResult?
}