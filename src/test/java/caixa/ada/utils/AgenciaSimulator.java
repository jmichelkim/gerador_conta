package caixa.ada.utils;

import caixa.ada.model.Agencia;

public class AgenciaSimulator {

    public static Agencia criaAgencia(){
        Agencia agencia = new Agencia(1L, 1234L, "Agencia Teste" , "BR");
        return agencia;
    }
}
