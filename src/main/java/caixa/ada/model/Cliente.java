package caixa.ada.model;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome_cliente")
    private String nomeCliente;

    @Column(name="cpf_cliente")
    private int cpfCliente;

    private String endereco;
    private String telefone;
    private String cep;

    public Cliente(){

    }

    public Cliente(Long id, String nomeCliente, int cpfCliente, String endereco,
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

    public int getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(int cpfCliente) {
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
}
