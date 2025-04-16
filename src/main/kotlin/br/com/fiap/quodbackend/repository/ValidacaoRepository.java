package br.com.fiap.quodbackend.repository;

import br.com.fiap.quodbackend.model.BiometriaRequest;
import br.com.fiap.quodbackend.model.ValidacaoResponse;
import lombok.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ValidacaoRepository extends MongoRepository<ValidacaoDocument, String> {
    default void save(BiometriaRequest request, ValidacaoResponse response) {
        ValidacaoDocument document = new ValidacaoDocument();
        document.setTransacaoId(request.getTransacaoId());
        document.setTipoBiometria(request.getTipoBiometria());
        document.setResponse(response);
        document.setTimestamp(LocalDateTime.now());
        save(document);
    }
}


@Data
class ValidacaoDocument {
    private String id;
    private String transacaoId;
    private String tipoBiometria;
    private ValidacaoResponse response;
    private LocalDateTime timestamp;
}