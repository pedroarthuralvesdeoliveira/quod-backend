package br.com.fiap.quodbackend.service

import br.com.fiap.quodbackend.model.FraudNotification
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class NotificationService(
    private val webClientBuilder: WebClient.Builder
) {
    fun notifyFraud(notification: FraudNotification) {
        val webClient = webClientBuilder.build()
        webClient.post()
            .uri("/api/notificacoes/fraude")
            .bodyValue(notification)
            .retrieve()
            .bodyToMono(Void::class.java)
            .subscribe()
    }
}