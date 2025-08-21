package caixa.ada.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero_conta")
    private Long numeroConta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Agencia agencia;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;

    @Column(name="data_abertura")
    private LocalDate dataAbertura;

    @Column(name="data_encerramento")
    private LocalDate dataEncerramento;

    private Boolean isAtive;

    private static Long contador = 1L;

    public Conta(){

    }
    public Conta(Agencia agencia, Cliente cliente) {
        this.numeroConta = contador;
        this.agencia = agencia;
        this.cliente = cliente;
        this.dataAbertura = LocalDate.now();
        this.dataEncerramento = null;
        this.isAtive = true;
        this.contador++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento() {
        this.dataEncerramento = LocalDate.now();
        this.setAtive(false);
    }

    public Boolean getAtive() {
        return isAtive;
    }

    public void setAtive(Boolean ative) {
        isAtive = ative;
    }
}
