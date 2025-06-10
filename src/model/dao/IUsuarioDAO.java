package model.dao;

import javafx.collections.ObservableList;
import model.dto.UsuarioDTO;

public interface IUsuarioDAO {    
    void cadastrarUsuario(UsuarioDTO usuario);              // Salvar um novo usuário
    void atualizarUsuario(UsuarioDTO usuario);           // Atualizar um usuário existente
    void deletarUsuario(UsuarioDTO usuario);                      // Remover um usuário pelo ID
    void selecionarUsuario();               // Buscar um usuário pelo ID
    ObservableList<UsuarioDTO> listarUsuarios() ;               // Listar todos os usuários
}
