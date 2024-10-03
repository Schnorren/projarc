package com.projarc.sarc.exception;

/**
 * Exceção personalizada para violações de integridade de dados.
 * Esta exceção é lançada quando uma operação tenta violar as regras de integridade definidas no sistema.
 */
public class DataIntegrityException extends RuntimeException {

    /**
     * Construtor padrão sem argumentos.
     */
    public DataIntegrityException() {
        super();
    }

    /**
     * Construtor que recebe uma mensagem personalizada.
     *
     * @param message Mensagem descritiva da exceção.
     */
    public DataIntegrityException(String message) {
        super(message);
    }

    /**
     * Construtor que recebe uma mensagem personalizada e uma causa.
     *
     * @param message Mensagem descritiva da exceção.
     * @param cause   Causa original da exceção.
     */
    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor que recebe uma causa.
     *
     * @param cause Causa original da exceção.
     */
    public DataIntegrityException(Throwable cause) {
        super(cause);
    }
}
