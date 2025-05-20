package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    static String stringconexao = "jdbc:postgresql://localhost:5432/DBMVC";//conexão ao banco de dados criado por mim.
    static String usuario = "postgres";
    static String senha = "postgres";

    public Connection getConnection() {
        /*
            método público, que retorna uma instância de 'Connection',
            onde fornece uma forma de estabelecer uma conexão com o BD.
        */
        try {
            return DriverManager.getConnection(stringconexao, usuario, senha);
            // Tenta obter conexão com o banco de dados usando as informações fornecidas.
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
            // Mensagem de erro, caso não consiga fazer a conexão.
        }
    }
}
