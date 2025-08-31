package caixa.ada.report;

import caixa.ada.DTO.RelatorioDTO;
import caixa.ada.DTO.ContaResumoDTO;
import caixa.ada.model.Agencia;
import caixa.ada.model.Conta;
import caixa.ada.repository.ContaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RelatorioService {

    @Inject
    EntityManager em;

    @Inject
    ContaRepository contaRepository;

    public RelatorioDTO gerarRelatorioDaAgencia(Long agenciaId) {
        Agencia agencia = em.find(Agencia.class, agenciaId);
        if (agencia == null) {
            throw new IllegalArgumentException("Agência com ID " + agenciaId + " não encontrada.");
        }

        List<Conta> contas = contaRepository.findContasAtivasPorAgencia(agenciaId);

        List<ContaResumoDTO> contasResumo = contas.stream()
                .map(conta -> new ContaResumoDTO(
                        conta.getNumeroConta(),
                        conta.getCliente().getNomeCliente(),
                        conta.getCliente().getCpfCliente(),
                        conta.getCliente().getTelefone()
                ))
                .collect(Collectors.toList());

        return new RelatorioDTO(
                agencia.getId(),
                agencia.getNomeAgencia(),
                contasResumo.size(),
                contasResumo
        );
    }
}