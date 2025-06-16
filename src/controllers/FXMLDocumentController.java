package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.UsuarioDAO;
import model.dto.UsuarioDTO;
import util.DialogUtil;
import validator.IUsuarioValidator;
// FXMLDocumentController (Módulo de Alto Nível) depende de Abstração
public class FXMLDocumentController implements Initializable {
    /*
        Variáveis utulizadas nas aplicações
    */
    @FXML
    private TextField txtNome, txtLogin, txtEmail, txtSenha;
    @FXML
    private Button btnCadastrar, btnSelecionar, btnDeletar, btnAtualizar;
    @FXML
    private TableView<UsuarioDTO> tblUsers;
    @FXML
    private TableColumn<UsuarioDTO, Integer> colId;
    @FXML
    private TableColumn<UsuarioDTO, String> colNome, colLogin, colEmail, colSenha;
    private DialogUtil mensagem = new DialogUtil();
    private final IUsuarioValidator usuarioValidator;
    /*
        Realiza o cadastro do usuário pegando as informações das labels 
        e chama a função cadastrar (model.dao/UsuarioDAO)
    */
    // Injeção de dependência via construtor
    public FXMLDocumentController(IUsuarioValidator usuarioValidator){
        this.usuarioValidator = usuarioValidator;
    }
    @FXML
    private void cadastrarUsuario(ActionEvent event) {
        String nome = txtNome.getText();
        String login = txtLogin.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        if(!usuarioValidator.validarUsuario(nome, email, senha, login)){
            return;
        }
        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome(nome);
        objUsuarioDTO.setLogin(login);
        objUsuarioDTO.setEmail(email);
        objUsuarioDTO.setSenha(senha);

        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        objUsuarioDAO.cadastrarUsuario(objUsuarioDTO);
     
        carregarUsuarios();
        limparCampos();
    }
    /*
        Método que pega as informações de um usuário selecionadas 
        na tabela para serem atualizadas ou deletadas 
    */
    @FXML
    private void selecionarUsuario() {
        UsuarioDTO objUsuarioDTO = (UsuarioDTO) tblUsers.getSelectionModel().getSelectedItem();
        if (objUsuarioDTO != null) {
            txtNome.setText(objUsuarioDTO.getNome());
            txtLogin.setText(objUsuarioDTO.getLogin());
            txtEmail.setText(objUsuarioDTO.getEmail());
            txtSenha.setText(objUsuarioDTO.getSenha());
        }
    }
    /*
        Método que atualiza as informaçõesd o usuário selecionado.
    */
    @FXML
    private void atualizarUsuario(ActionEvent event) {
        UsuarioDTO objUsuarioDTO = (UsuarioDTO) tblUsers.getSelectionModel().getSelectedItem();
        String nome = txtNome.getText();
        String login = txtLogin.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        if(!usuarioValidator.validarUsuario(nome, email, senha, login)){
            return;
        }
        if (objUsuarioDTO != null) {
            objUsuarioDTO.setNome(nome);
            objUsuarioDTO.setLogin(login);
            objUsuarioDTO.setEmail(email);
            objUsuarioDTO.setSenha(senha);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.atualizarUsuario(objUsuarioDTO);
            carregarUsuarios();
            selecionarUsuario();
        }
        limparCampos();
    }
    /*
        Método que deleta o usuário selecionado.
    */
    @FXML
    private void deletarUsuario(ActionEvent event) {
        UsuarioDTO objUsuarioDTO = (UsuarioDTO) tblUsers.getSelectionModel().getSelectedItem();
        if (objUsuarioDTO != null) {
            if(mensagem.showConfirmation("Confirmar", "Voce deseja deletar esse usuário?")){
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.deletarUsuario(objUsuarioDTO);
                carregarUsuarios();
                selecionarUsuario();
            }
        }
    }
    /*
        Método que nomeia as colunas da tabela e, ao rodar 
        o comando carregarUsuarios, lista todos os usuários.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        carregarUsuarios();
    }  
    /*
        Método para listagem dos usuários. 
    */
    private void carregarUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<UsuarioDTO> listaUsuarios = usuarioDAO.listarUsuarios();
        tblUsers.setItems(listaUsuarios);
    }
    /*
        Método para limpagem das labels.
    */
    @FXML
    private void limparCampos() {
        txtNome.setText(""); 
        txtLogin.setText(""); 
        txtEmail.setText(""); 
        txtSenha.setText("");
    }
}
