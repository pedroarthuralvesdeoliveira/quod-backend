package br.com.fiap.quodbackend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class NotificacaoFraude {
    @NotBlank
    private String transacaoId;
    @NotBlank
    private String tipoBiometria;
    @NotBlank
    private String tipoFraude;
    @NotBlank
    private LocalDateTime dataCaptura;
    @NotNull
    private BiometriaRequest.Dispositivo dispositivo;
    @NotEmpty
    private List<String> canalNotificacao;
    @NotBlank
    private String notificadoPor;
    @NotNull
    private BiometriaRequest.Metadados metadados;
}
