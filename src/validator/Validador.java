package validator; 

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static util.DialogUtil.showAlert;

/*
 *  Certifique-se de que todas as classes estejam no mesmo pacote 'validator'
 *  Interface para definir um contrato de validação
 */
public interface Validador<T>{
    boolean validar(T valor); // O método validar recebe o valor para que o Validador possa ser mais genérico
    String getMensagemErro();
    T getValor(); // Novo método para retornar o valor que está sendo validado
}

class CampoObrigatorioValidador implements Validador<String> {
    private final String campo;
    private final String valor; // Armazena o valor a ser validado

    public CampoObrigatorioValidador(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }
    // o valor do parâmetro é o que será validado nesse ciclo
    @Override
    public boolean validar(String valor) { 
        return valor != null && !valor.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "O campo '" + campo + "' é obrigatório.";
    }

    @Override
    public String getValor() {
        return valor;
    }
}

class EmailValidador implements Validador<String> {
    private static final String EMAIL_REGEX = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$";
    private final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    private final String email; // Armazena o e-mail a ser validado

    public EmailValidador(String email) {
        this.email = email;
    }
    // o valor do parâmetro é o que será validado nesse ciclo
    @Override
    public boolean validar(String valor) { 
        return valor != null && valor.matches(EMAIL_REGEX);
    }

    @Override
    public String getMensagemErro() {
        return "E-mail inválido: " + email;
    }

    @Override
    public String getValor() {
        return email;
    }
}
