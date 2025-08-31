package caixa.ada.DTO;

import java.util.List;

public class RelatorioDTO {

    private Long idAgencia;
    private String nomeAgencia;
    private int quantidadeContas;
    private List<ContaResumoDTO> contas;

    public RelatorioDTO() {}

    public RelatorioDTO(Long idAgencia, String nomeAgencia, int quantidadeContas, List<ContaResumoDTO> contas) {
        this.idAgencia = idAgencia;
        this.nomeAgencia = nomeAgencia;
        this.quantidadeContas = quantidadeContas;
        this.contas = contas;
    }

    // Getters e Setters

    public Long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public int getQuantidadeContas() {
        return quantidadeContas;
    }

    public void setQuantidadeContas(int quantidadeContas) {
        this.quantidadeContas = quantidadeContas;
    }

    public List<ContaResumoDTO> getContas() {
        return contas;
    }

    public void setContas(List<ContaResumoDTO> contas) {
        this.contas = contas;
    }
}