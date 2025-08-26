package caixa.ada.utils;


import caixa.ada.DTO.ClienteDTO;

public class ClienteSimulator {

    public static ClienteDTO criaCliente(String cep){
        ClienteDTO cliente= new ClienteDTO();
        cliente.setNome("TesteCliente");
        cliente.setCpf("12345678900");
        cliente.setEndereco("Endereco Cliente1");
        cliente.setTelefone("11-2222-3333");
        cliente.setCep(cep);
        return cliente;
    }
}
