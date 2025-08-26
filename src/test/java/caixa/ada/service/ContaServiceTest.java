package caixa.ada.service;

import caixa.ada.exceptions.AgenciaNaoEncontradaException;
import caixa.ada.exceptions.CepNaoEncontradoOuNuloException;
import caixa.ada.model.Conta;
import caixa.ada.model.http.AgenciaHttp;
import caixa.ada.repository.ContaRepository;
import caixa.ada.service.http.ConsultaCepHttpService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static caixa.ada.utils.AgenciaSimulator.criaAgencia;
import static caixa.ada.utils.ClienteSimulator.criaCliente;

@QuarkusTest
public class ContaServiceTest {

    @InjectMock
    private ContaRepository contaRepository;

    @Inject
    @InjectMock
    @RestClient
    private ConsultaCepHttpService consultaCepHttpService;

    @Inject
    private ContaService contaService;

    @BeforeEach
    public void setUp() {
        Mockito.doNothing().when(contaRepository).persist(Mockito.any(Conta.class));
    }

    @Test
    public void deveNaoCadastrarQuandoConsultaCepRetonaNull() {
        Conta conta = new Conta();
        Mockito.when(consultaCepHttpService.buscaCep("21000")).thenReturn(null);
        Assertions.assertThrows(CepNaoEncontradoOuNuloException.class,
                () -> contaService.cadastrarConta(criaCliente("21000")));
        Mockito.verify(contaRepository, Mockito.never()).persist(conta);
    }

    @Test
    public void deveNaoCadastrarQuandoBuscaAgenciaRetonaNull(){
        Conta conta = new Conta();
        Mockito.when(consultaCepHttpService.buscaCep("21000-000")).thenReturn(new AgenciaHttp("21000-000", null));
        Assertions.assertThrows(AgenciaNaoEncontradaException.class,
                () -> contaService.cadastrarConta(criaCliente("21000-000")));
        Mockito.verify(contaRepository, Mockito.never()).persist(conta);
    }

    @Test
    public void deveCadastrarContaEmAgenciaExistente() {
        var cliente = criaCliente("01001-000");
        var agencia = criaAgencia();
        var conta = new Conta();
        Mockito.when(consultaCepHttpService.buscaCep("01001-000")).thenReturn(new AgenciaHttp("01001-000", "BR"));
        contaService.cadastrarConta(criaCliente("01001-000"));
        Mockito.verify(contaRepository, Mockito.never()).persist(conta);
    }
}
