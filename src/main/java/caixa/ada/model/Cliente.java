package caixa.ada.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome_cliente")
    private String nomeCliente;

    @Column(name="cpf_cliente")
    private String cpfCliente;

    private String endereco;
    private String telefone;
    private String cep;

    public Cliente(){

    }

    public Cliente(Long id, String nomeCliente, String cpfCliente, String endereco,
                   String telefone, String cep) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(id, cliente.id) && Objects.equals(nomeCliente, cliente.nomeCliente) && Objects.equals(cpfCliente, cliente.cpfCliente) && Objects.equals(endereco, cliente.endereco) && Objects.equals(telefone, cliente.telefone) && Objects.equals(cep, cliente.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCliente, cpfCliente, endereco, telefone, cep);
    }
}
