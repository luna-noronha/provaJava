package validator;

import java.util.regex.Pattern;

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