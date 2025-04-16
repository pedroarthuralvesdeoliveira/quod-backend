package br.com.fiap.quodbackend.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BiometriaRequest {
    @NotBlank
    private String transacaoId;
    @NotBlank
    private String tipoBiometria; // "facial" ou "digital"
    @NotNull
    private byte[] imagem;
    @NotNull
    private Dispositivo dispositivo;
    @NotNull
    private Metadados metadados;

    @Data
    public static class Dispositivo {
        @NotBlank
        private String fabricante;
        @NotBlank
        private String modelo;
        @NotBlank
        private String sistemaOperacional;
    }

    @Data
    public static class Metadados {
        private double latitude;
        private double longitude;
        @NotBlank
        private String ipOrigem;
    }
}