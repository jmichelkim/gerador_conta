package caixa.ada.repository;

import caixa.ada.model.Conta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContaRepository implements PanacheRepository<Conta> {

}
