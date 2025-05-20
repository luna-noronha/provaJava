package validator; 
/*
 *  Certifique-se de que todas as classes estejam no mesmo pacote 'validator'
 *  Interface para definir um contrato de validação
 */
public interface Validador<T>{
    boolean validar(T valor); // O método validar recebe o valor para que o Validador possa ser mais genérico
    String getMensagemErro();
    T getValor(); // Novo método para retornar o valor que está sendo validado
}
