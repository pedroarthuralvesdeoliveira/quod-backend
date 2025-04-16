package br.com.fiap.quodbackend.service;

import br.com.fiap.quodbackend.model.NotificacaoFraude;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoService {
    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarNotificacao(NotificacaoFraude notificacao) {
        // Simular envio de notificação para o sistema de monitoramento
        String url = "http://sistema-monitoramento/api/notificacoes/fraude";
        try {
            restTemplate.postForObject(url, notificacao, Void.class);
        } catch (Exception e) {
            // Logar erro (em produção, usar um logger)
            System.err.println("Erro ao enviar notificação: " + e.getMessage());
        }
    }
}