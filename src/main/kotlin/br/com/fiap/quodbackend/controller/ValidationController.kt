package br.com.fiap.quodbackend.controller

import br.com.fiap.quodbackend.model.ValidationRequest
import br.com.fiap.quodbackend.model.ValidationResult
import br.com.fiap.quodbackend.service.ValidationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/validations")
class ValidationController(
    private val validationService: ValidationService
) {
    @PostMapping("/biometric")
    fun validateBiometric(
        @RequestPart("image") image: MultipartFile,
        @RequestPart("request") request: ValidationRequest
    ): ResponseEntity<ValidationResult> {
        val result = validationService.validateBiometric(
            request.copy(image = image.bytes)
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/document")
    fun validateDocument(
        @RequestPart("image") image: MultipartFile,
        @RequestPart("request") request: ValidationRequest
    ): ResponseEntity<ValidationResult> {
        val result = validationService.validateDocument(
            request.copy(image = image.bytes)
        )
        return ResponseEntity.ok(result)
    }
}