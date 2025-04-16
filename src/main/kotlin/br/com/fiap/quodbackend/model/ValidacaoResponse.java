package br.com.fiap.quodbackend.model;

import lombok.Data;

@Data
public class ValidacaoResponse {
    private boolean sucesso;
    private String mensagem;
    private String tipoFraude; // Opcional, preenchido se fraude for detectada
}