package caixa.ada.service;

import caixa.ada.DTO.mapper.ClienteMapper;
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
import static caixa.ada.utils.ContaSimulator.criaConta;

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
                .thenReturn(new AgenciaHttp("01001-000", "SP"));
        contaService.cadastrarConta(criaCliente("01001-000"));
        Mockito.verify(contaRepository, Mockito.times(1)).persist(Mockito.any(Conta.class));
    }

    @Test
    public void deveNaoCadastrarQuandoConsultaCepRetonaNull() {
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
    public void deveEncerrarContaExistente() {
        Conta conta = criaConta();
        Mockito.when(contaRepository.findById(1L)).thenReturn(conta);
        contaService.encerrarConta(1L);
        Assertions.assertNotNull(conta.getDataEncerramento());
        Assertions.assertFalse(conta.getActive());
    }

    @Test
    public void deveLancarExcecaoAoTentarEncerrarContaInexistente() {
        ContaNaoEncontradaException ex = Assertions.assertThrows(ContaNaoEncontradaException.class,
                () -> contaService.encerrarConta(0L));
        Assertions.assertEquals("Não encontrada conta para encerrar com 'id' 0", ex.getMessage());
    }

    @Test
    public void deveDeletarContaExistente() {
        Conta conta = criaConta();
        Mockito.when(contaRepository.findById(1L)).thenReturn(conta);
        contaService.deletarConta(1L);
        Mockito.verify(contaRepository, Mockito.times(1)).delete(conta);
    }

    @Test
    public void deveLancarExcecaoAoTentarDeletarContaInexistente() {
        ContaNaoEncontradaException ex = Assertions.assertThrows(ContaNaoEncontradaException.class,
                () -> contaService.deletarConta(0L));
        Assertions.assertEquals("Não encontrada conta para deletar com 'id' 0", ex.getMessage());
    }

    @Test
    public void deveAlterarContaExistente() {
        Conta conta = criaConta();
        Mockito.when(contaRepository.findById(1L)).thenReturn(conta);
        var clienteDTO = criaCliente("58135-000");
        contaService.alterarConta(1L, clienteDTO);
        var clienteNovo = ClienteMapper.toEntity(clienteDTO);
        Assertions.assertEquals(conta.getCliente(), clienteNovo);
    }

    @Test
    public void deveLancarExcecaoAoTentarAlterarContaInexistente() {
        ContaNaoEncontradaException ex = Assertions.assertThrows(ContaNaoEncontradaException.class,
                () -> contaService.alterarConta(0L, criaCliente("01001-000")));
        Assertions.assertEquals("Não encontrada conta para alterar com 'id' 0", ex.getMessage());
    }
}
