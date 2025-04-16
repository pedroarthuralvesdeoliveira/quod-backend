package br.com.fiap.quodbackend.util;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Component;

@Component
public class ImageValidator {
    static {
        nu.pattern.OpenCV.loadShared();
    }

    public boolean validarImagemBasica(byte[] imagem) {
        // Verificar formato, tamanho e qualidade
        try {
            Mat mat = Imgcodecs.imdecode(new Mat(), Imgcodecs.IMREAD_COLOR);
            if (mat.empty()) {
                return false;
            }
            // Verificar tamanho (exemplo: mínimo 100x100 pixels)
            if (mat.width() < 100 || mat.height() < 100) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String validarImagemAvancada(byte[] imagem) {
        // Simulação de detecção de fraude (deepfake, máscara, etc.)
        // Aqui, usamos uma regra simples: imagens com baixa qualidade podem ser suspeitas
        try {
            Mat mat = Imgcodecs.imdecode(new Mat(), Imgcodecs.IMREAD_COLOR);
            if (mat.empty()) {
                return "formato_invalido";
            }
            // Simulação: se a imagem for muito pequena, consideramos "foto de foto"
            if (mat.width() < 200 || mat.height() < 200) {
                return "foto_de_foto";
            }
            // TODO: Adicionar lógica mais avançada (ex.: análise de textura para deepfake)
            return null; // Sem fraude
        } catch (Exception e) {
            return "erro_processamento";
        }
    }
}