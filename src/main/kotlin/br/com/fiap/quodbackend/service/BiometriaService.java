package br.com.fiap.quodbackend.service;

import br.com.fiap.quodbackend.model.BiometriaRequest;
import br.com.fiap.quodbackend.model.NotificacaoFraude;
import br.com.fiap.quodbackend.model.ValidacaoResponse;
import br.com.fiap.quodbackend.repository.ValidacaoRepository;
import br.com.fiap.quodbackend.util.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class BiometriaService {
    @Autowired
    private ImageValidator imageValidator;
    @Autowired
    private NotificacaoService notificacaoService;
    @Autowired
    private ValidacaoRepository validacaoRepository;

    public ValidacaoResponse validarBiometria(BiometriaRequest request) {
        ValidacaoResponse response = new ValidacaoResponse();

        // Validação básica
        if (!imageValidator.validarImagemBasica(request.getImagem())) {
            response.setSucesso(false);
            response.setMensagem("Imagem inválida: formato ou tamanho incorreto.");
            return response;
        }

        // Validação avançada
        String tipoFraude = imageValidator.validarImagemAvancada(request.getImagem());
        if (tipoFraude != null) {
            response.setSucesso(false);
            response.setMensagem("Fraude detectada: " + tipoFraude);
            response.setTipoFraude(tipoFraude);

            // Enviar notificação de fraude
            NotificacaoFraude notificacao = new NotificacaoFraude();
            notificacao.setTransacaoId(request.getTransacaoId());
            notificacao.setTipoBiometria(request.getTipoBiometria());
            notificacao.setTipoFraude(tipoFraude);
            notificacao.setDataCaptura(LocalDateTime.now());
            notificacao.setDispositivo(request.getDispositivo());
            notificacao.setCanalNotificacao(Arrays.asList("sms", "email"));
            notificacao.setNotificadoPor("sistema-de-monitoramento");
            notificacao.setMetadados(request.getMetadados());
            notificacaoService.enviarNotificacao(notificacao);
        } else {
            response.setSucesso(true);
            response.setMensagem("Biometria validada com sucesso.");
        }

        // Persistir validação
        validacaoRepository.save(request, response);
        return response;
    }
}