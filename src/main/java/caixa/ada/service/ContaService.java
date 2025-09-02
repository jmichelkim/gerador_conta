package caixa.ada.service;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.DTO.RelatorioDTO;
import caixa.ada.DTO.mapper.ClienteMapper;
import caixa.ada.exceptions.AgenciaNaoEncontradaException;
import caixa.ada.exceptions.CepNaoEncontradoOuNuloException;
import caixa.ada.exceptions.ContaNaoEncontradaException;
import caixa.ada.log.ContaLog;
import caixa.ada.model.Agencia;
import caixa.ada.model.Cliente;
import caixa.ada.model.Conta;
import caixa.ada.model.http.AgenciaHttp;
import caixa.ada.report.RelatorioService;
import caixa.ada.repository.ContaRepository;
import caixa.ada.service.http.ConsultaCepHttpService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ContaService {

    @RestClient
    @Inject
    private ConsultaCepHttpService consultaCepHttpService;

    @Inject
    ContaLog contaLog;

    private final ContaRepository contaRepository;
    private final AgenciaService agenciaService;

    public ContaService(ContaRepository contaRepository, AgenciaService agenciaService) {
        this.contaRepository = contaRepository;
        this.agenciaService = agenciaService;
    }

    public void cadastrarConta(ClienteDTO clienteDTO){
        AgenciaHttp agenciaHttp = obterAgenciaPorCep(clienteDTO.getCep());
        if (agenciaHttp.getUf() == null) {
            throw new AgenciaNaoEncontradaException("Agência não encontrada para o estado informado.");
        }
        Agencia agencia = agenciaService.buscarUf(agenciaHttp.getUf());
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Conta conta = new Conta(agencia, cliente);
        contaRepository.persist(conta);
        contaLog.registrarLog("CREATE", "Conta cadastrada",conta);
    }

    public AgenciaHttp obterAgenciaPorCep(String cep){
        try {
            AgenciaHttp agencia = consultaCepHttpService.buscaCep(cep);
            if (agencia == null) {
                throw new CepNaoEncontradoOuNuloException("CEP inválido ou não encontrado.");
            }
            return agencia;
        } catch (Exception e) {
            throw new CepNaoEncontradoOuNuloException("Erro ao consultar o CEP.");
        }
    }

    public List<Conta> getContas() {
        return contaRepository.listAll();
    }

    public Object findById(Long id){
        return contaRepository.findById(id);
    }

    public void alterarConta(Long id, ClienteDTO clienteDTO){
        Conta contaEncontrada = contaRepository.findById(id);
        if (contaEncontrada == null) {
            throw new ContaNaoEncontradaException("Não encontrada conta para alterar com 'id' " + id);
        }
        this.alterarDadosCliente(contaEncontrada, clienteDTO);
        contaLog.registrarLog("PUT", "Conta alterada",contaEncontrada);
    }

    public void encerrarConta(Long id) {
        Conta contaEncontrada = contaRepository.findById(id);
        if (contaEncontrada == null) {
            throw new ContaNaoEncontradaException("Não encontrada conta para encerrar com 'id' " + id);
        }
        contaEncontrada.setDataEncerramento();
        contaLog.registrarLog("PATCH", "Conta encerrada",contaEncontrada);
    }

    public void deletarConta(Long id) {
        Conta contaEncontrada = contaRepository.findById(id);
        if (contaEncontrada == null) {
            throw new ContaNaoEncontradaException("Não encontrada conta para deletar com 'id' "+id);
        }
        contaLog.registrarLog("DELETE", "Conta deletada",contaEncontrada);
        contaRepository.delete(contaEncontrada);
    }

    @Inject
    RelatorioService relatorioService;
    public RelatorioDTO gerarRelatorioPorAgencia(Long idAgencia) {
        return relatorioService.gerarRelatorioDaAgencia(idAgencia);
    }

    private void alterarDadosCliente(Conta contaEncontrada, ClienteDTO clienteDTO){
        Cliente clienteExistente = contaEncontrada.getCliente();
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        clienteExistente.setNomeCliente(cliente.getNomeCliente());
        clienteExistente.setCep(cliente.getCep());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setTelefone(cliente.getTelefone());
    }
}
