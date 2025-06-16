package validator;

public interface IUsuarioValidator {
    boolean validarUsuario(String nome, String email, String senha, String login);
}