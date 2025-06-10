package validator;

public interface IUsuarioValidator {
    boolean validarUsuario(String nome, String email, String senha, String login);
    boolean validarNome(String nome);
    boolean validarEmail(String email);
    boolean validarSenha(String senha);
    boolean validarLogin(String login);
}