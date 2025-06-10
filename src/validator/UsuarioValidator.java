package validator;

import java.util.ArrayList;
import java.util.List;
import static util.DialogUtil.showAlert;
import validator.CampoObrigatorioValidador;

public class UsuarioValidator implements IUsuarioValidator {

    // Método principal de validação que combina todas as regras
    public boolean validarUsuario(String nome, String email, String senha, String login) {
        // Lista de validadores que serão aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("E-mail", email));
        validadores.add(new CampoObrigatorioValidador("Senha", senha));
        validadores.add(new CampoObrigatorioValidador("Login", login));

        // Adicionando o validador específico de formato de e-mail (aplicado ao campo email)
        validadores.add(new EmailValidador(email));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {  // O validador agora "conhece" o valor que vai validar
                showWarning(validador.getMensagemErro());
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }

    private void showWarning(String mensagem) {
        // Simulação do seu método showWarning
        showAlert("ERROR","" + mensagem);
        // Na sua aplicação JavaFX, você usaria DialogUtil.showWarning(mensagem);
    }

    @Override
    public boolean validarNome(String nome) {
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Nome", nome));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {  // O validador agora "conhece" o valor que vai validar
                showWarning(validador.getMensagemErro());
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }

    @Override
    public boolean validarEmail(String email) {
        // Lista de validadores que serão aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("E-mail", email));

        // Adicionando o validador específico de formato de e-mail (aplicado ao campo email)
        validadores.add(new EmailValidador(email));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {  // O validador agora "conhece" o valor que vai validar
                showWarning(validador.getMensagemErro());
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }

    @Override
    public boolean validarSenha(String senha) {
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Senha", senha));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {  // O validador agora "conhece" o valor que vai validar
                showWarning(validador.getMensagemErro());
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }

    @Override
    public boolean validarLogin(String login) {
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("Login", login));
        
        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {  // O validador agora "conhece" o valor que vai validar
                showWarning(validador.getMensagemErro());
                return false; // Retorna falso na primeira falha de validação
            }
        }
        return true; // Todos os validadores passaram
    }
}
