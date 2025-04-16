package br.com.fiap.quodbackend.controller;

import br.com.fiap.quodbackend.model.BiometriaRequest;
import br.com.fiap.quodbackend.model.ValidacaoResponse;
import br.com.fiap.quodbackend.service.BiometriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biometria")
public class BiometricController {
    @Autowired
    private BiometriaService biometriaService;

    @PostMapping("/facial")
    public ValidacaoResponse validarFacial(@RequestBody BiometriaRequest request) {
        request.setTipoBiometria("facial");
        return biometriaService.validarBiometria(request);
    }

    @PostMapping("/digital")
    public ValidacaoResponse validarDigital(@RequestBody BiometriaRequest request) {
        request.setTipoBiometria("digital");
        return biometriaService.validarBiometria(request);
    }
}