package caixa.ada.repository.log;

import caixa.ada.model.log.OperacaoLog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OperacaoLogRepository implements PanacheRepository<OperacaoLog> {

    public List<OperacaoLog> findByContaId(Long contaId) {

        return list("contaId", contaId);
    }


}
