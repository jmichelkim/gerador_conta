package caixa.ada.service;

import caixa.ada.exceptions.AgenciaNaoEncontradaException;
import caixa.ada.exceptions.CepNaoEncontradoOuNuloException;
import caixa.ada.exceptions.ContaNaoEncontradaException;
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
        Mockito.when(contaService.findById(0L)).thenReturn(null);
    }

    @Test
    public void deveCadastrarContaEmAgenciaExistente() {
        Mockito.when(consultaCepHttpService.buscaCep("01001-000"))
                .thenReturn(new AgenciaHttp("01001-000", "BR"));
        contaService.cadastrarConta(criaCliente("01001-000"));
        Mockito.verify(contaRepository).persist(Mockito.any(Conta.class));
    }

    @Test
    public void deveNaoCadastrarQuandoConsultaCepRetonaNull() {
        var conta = new Conta();
        Mockito.when(consultaCepHttpService.buscaCep("21000")).thenReturn(null);
        CepNaoEncontradoOuNuloException ex = Assertions.assertThrows(CepNaoEncontradoOuNuloException.class,
                () -> contaService.cadastrarConta(criaCliente("21000")));
        Assertions.assertEquals("Erro ao consultar o CEP.", ex.getMessage());
    }

    @Test
    public void deveNaoCadastrarQuandoBuscaAgenciaRetonaNull(){
        var conta = new Conta();
        Mockito.when(consultaCepHttpService.buscaCep("21000-000")).thenReturn(new AgenciaHttp("21000-000", null));
        AgenciaNaoEncontradaException ex = Assertions.assertThrows(AgenciaNaoEncontradaException.class,
                () -> contaService.cadastrarConta(criaCliente("21000-000")));
        Mockito.verify(contaRepository, Mockito.never()).persist(conta);
        Assertions.assertEquals("Agência não encontrada para o estado informado.", ex.getMessage());
    }

    @Test
    public void deveLancarExcecaoAoTentarEncerrarContaInexistente() {
        ContaNaoEncontradaException ex = Assertions.assertThrows(ContaNaoEncontradaException.class,
                () -> contaService.encerrarConta(0L));
        Assertions.assertEquals("Não encontrada conta para encerrar com esse 'id'.", ex.getMessage());
    }

    @Test
    public void deveLancarExcecaoAoTentarDeletarContaInexistente() {
        ContaNaoEncontradaException ex = Assertions.assertThrows(ContaNaoEncontradaException.class,
                () -> contaService.deletarConta(0L));
        Assertions.assertEquals("Não encontrada conta para deletar com esse 'id'.", ex.getMessage());
    }




}
