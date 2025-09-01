package caixa.ada.log;


import caixa.ada.model.Conta;
import caixa.ada.model.log.OperacaoLog;
import caixa.ada.repository.log.OperacaoLogRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
public class ContaLog {

    @Transactional
    public void registrarLog(String tipo, String descricao, Conta conta) {
        OperacaoLogRepository operacaoLogRepository= new OperacaoLogRepository();
        OperacaoLog log = new OperacaoLog();
        log.setTipoOperacao(tipo);
        log.setDataOperacao(LocalDateTime.now());
        log.setDescricao(descricao);
        log.setContaId(conta.getId());
        log.setNumeroConta(conta.getNumeroConta());
        log.setAgenciaId(conta.getAgencia().getId());
        log.setNomeAgencia(conta.getAgencia().getNomeAgencia());
        log.setClienteId(conta.getCliente().getId());
        log.setNomeCliente(conta.getCliente().getNomeCliente());
        operacaoLogRepository.persist(log);
    }
}
