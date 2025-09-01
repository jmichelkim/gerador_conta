package caixa.ada.utils;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.DTO.mapper.ClienteMapper;
import caixa.ada.model.Agencia;
import caixa.ada.model.Conta;

public class ContaSimulator {

    public static Conta criaConta(){
        ClienteDTO clienteDTO = ClienteSimulator.criaCliente("01001-000");
        Agencia agencia = new Agencia(1L, 1L, "Teste", "BR");
        Conta conta = new Conta(agencia, ClienteMapper.toEntity(clienteDTO));
        conta.setId(1L);
        return conta;
    }
}
