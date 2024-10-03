package com.projarc.sarc.exception;

/**
 * Exceção personalizada para situações onde um recurso não é encontrado.
 * Esta exceção é lançada quando uma operação tenta acessar um recurso inexistente no sistema.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor padrão sem argumentos.
     */
    public ResourceNotFoundException() {
        super();
    }

    /**
     * Construtor que recebe uma mensagem personalizada.
     *
     * @param message Mensagem descritiva da exceção.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor que recebe uma mensagem personalizada e uma causa.
     *
     * @param message Mensagem descritiva da exceção.
     * @param cause   Causa original da exceção.
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor que recebe uma causa.
     *
     * @param cause Causa original da exceção.
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
