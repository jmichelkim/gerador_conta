package caixa.ada.exceptions;

public class AgenciaNaoEncontradaException extends RuntimeException {
    public AgenciaNaoEncontradaException(String message) {
        super(message);
    }
}
