package caixa.ada.model;

import jakarta.persistence.*;

@Entity
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="numero_agencia")
    public Long numeroAgencia;

    @Column(name="nome_agencia")
    public String nomeAgencia;

    @Column(name="uf_agencia")
    public String ufAgencia;

    public Agencia(){

    }

    public Agencia(Long id, Long numeroAgencia, String nomeAgencia, String ufAgencia) {
        this.id = id;
        this.numeroAgencia = numeroAgencia;
        this.nomeAgencia = nomeAgencia;
        ufAgencia = ufAgencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Long numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public String getUfAgencia() {
        return ufAgencia;
    }

    public void setUfAgencia(String ufAgencia) {
        ufAgencia = ufAgencia;
    }
}
