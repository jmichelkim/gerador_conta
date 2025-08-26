package caixa.ada.model.http;

public class AgenciaHttp {

    private String uf;
    private String cep;

    public AgenciaHttp() {

    }
    public AgenciaHttp(String code, String state){
        this.cep = code;
        this.uf = state;
    }



    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
