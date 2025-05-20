package validator;

public class CampoObrigatorioValidador implements Validador<String> {
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