package caixa.ada.repository;

import caixa.ada.model.Conta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ContaRepository implements PanacheRepository<Conta> {

    public List<Conta> findContasAtivasPorAgencia(Long agenciaId) {
        return list("agencia.id = ?1 and ativa = true", agenciaId);
    }

}
