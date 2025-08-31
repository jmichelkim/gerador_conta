package caixa.ada.DTO;

public class ContaResumoDTO {

    private Long numeroConta;
    private String nomeTitular;
    private String cpfTitular;
    private String telefoneTitular;

    public ContaResumoDTO() {}

    public ContaResumoDTO(Long numeroConta, String nomeTitular, String cpfTitular, String telefoneTitular) {
        this.numeroConta = numeroConta;
        this.nomeTitular = nomeTitular;
        this.cpfTitular = cpfTitular;
        this.telefoneTitular = telefoneTitular;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public String getTelefoneTitular() {
        return telefoneTitular;
    }

    public void setTelefoneTitular(String telefoneTitular) {
        this.telefoneTitular = telefoneTitular;
    }
}