package caixa.ada.service;

import caixa.ada.model.Agencia;
import caixa.ada.repository.AgenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public Agencia buscarUf(String uf) {
        return agenciaRepository.find("ufAgencia", uf).firstResult();
    }
}
