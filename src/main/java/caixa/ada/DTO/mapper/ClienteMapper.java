package caixa.ada.DTO.mapper;

import caixa.ada.DTO.ClienteDTO;
import caixa.ada.model.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCep(clienteDTO.getCep());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setNomeCliente(clienteDTO.getNome());
        cliente.setCpfCliente(clienteDTO.getCpf());
        return cliente;
    }
}
