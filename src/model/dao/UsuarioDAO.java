package model.dao;

import model.Conexao;
import model.dto.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDAO implements IUsuarioDAO {

    Connection c;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();

    public ObservableList<UsuarioDTO> listarUsuarios() {
        ObservableList<UsuarioDTO> listaUsuarios = FXCollections.observableArrayList();
        String sql = "SELECT * FROM usuario";

        try (Connection c = new Conexao().getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsuarioDTO usuario = new UsuarioDTO();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setLogin(rs.getString("login"));
                    listaUsuarios.add(usuario);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    /*
     * Realiza a partir do script SQL a inserção no banco de dados com as
     * informações passadas no objeto usuario.
     */
    public void cadastrarUsuario(UsuarioDTO usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, login) VALUES (?, ?, ?, ?)";
        ps = null;
        c = new Conexao().getConnection();
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getLogin());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     * Realiza a seleção de todos os usuarios, analogo ao modo de cadastro.
     */
    public void selecionarUsuario() {
        rs = null;
        ps = null;
        c = new Conexao().getConnection();
        try {
            ps = c.prepareStatement("select * from usuario");
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("#" + rs.getInt("id") + " # " + rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     * Atualiza as informações do usuario, com um script em SQL.
     */
    public void atualizarUsuario(UsuarioDTO usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, login = ? WHERE id = ?";
        ps = null;
        c = new Conexao().getConnection();

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getLogin());
            ps.setInt(5, usuario.getId()); // Atualiza com base no ID
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    /*
     * Esse método busca o usuário pelo email de login.
     * 
     */
    public UsuarioDTO buscarUsuarioPorLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        rs = null;
        ps = null;
        c = new Conexao().getConnection();
        UsuarioDTO usuario = null; // Inicializa a variável para armazenar o usuário

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, login); // Define o email na consulta
            rs = ps.executeQuery();

            // Verifica se o usuário foi encontrado
            if (rs.next()) {
                usuario = new UsuarioDTO();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setLogin(rs.getString("login"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return usuario;
    }

    /*
     * Realiza a exclusão permanente do usuario a partir do e-mail.
     * O script SQL deixa mais claro
     */
    public void deletarUsuario(UsuarioDTO usuario) {
        String sql = "DELETE FROM usuario WHERE email = ?";
        ps = null;
        c = new Conexao().getConnection();

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, usuario.getEmail()); // Usando o email do objeto DTO
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
