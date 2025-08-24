package caixa.ada.service;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.DTO.mapper.ClienteMapper;
import caixa.ada.model.Agencia;
import caixa.ada.model.Cliente;
import caixa.ada.model.Conta;
import caixa.ada.model.http.AgenciaHttp;
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

    private final ContaRepository contaRepository;
    private final AgenciaService agenciaService;

    public ContaService(ContaRepository contaRepository, AgenciaService agenciaService) {
        this.contaRepository = contaRepository;
        this.agenciaService = agenciaService;
    }

    public void cadastrarConta(ClienteDTO clienteDTO){
        AgenciaHttp agenciaHttp = consultaCepHttpService.buscaCep(clienteDTO.getCep());
        Agencia agencia = agenciaService.buscarUf(agenciaHttp.getUf());
        if (agencia !=null) {
            Cliente cliente = ClienteMapper.toEntity(clienteDTO);
            Conta conta = new Conta(agencia, cliente);
            contaRepository.persist(conta);
        }
        else System.out.println("Agencia não encontrada com o CEP informado");
    }


    public List<Conta> getContas() {
        return contaRepository.listAll();
    }

    public Object findById(Long id){
        return contaRepository.findById(id);
    }

    public void encerrarConta(Long id) {
        contaRepository.findById(id).setDataEncerramento();
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }
}
