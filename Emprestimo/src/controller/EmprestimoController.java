package controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EmprestimoModel;
import javafx.scene.control.Alert.AlertType;
import persistence.EmprestimoDAO;

public class EmprestimoController implements Initializable {

	EmprestimoDAO emDao = new EmprestimoDAO();

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtSalario;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtTelefone;

	@FXML
	private DatePicker date;

	@FXML
	private CheckBox isHomem;

	@FXML
	private CheckBox isMulher;

	@FXML
	private TextField txtValor;

	@FXML
	private TableView<EmprestimoModel> tabela;

	@FXML
	private TableColumn<EmprestimoModel, Boolean> selectCol;

	@FXML
	private TableColumn<EmprestimoModel, String> nomeCol;

	@FXML
	void onSolicitarProposta(ActionEvent event) {

		System.out.println("passou aqui");

		Connection conn;
		conn = emDao.abreConexaoBD();

		System.out.println("passou aqui 02");

		Alert alert;
		alert = new Alert(AlertType.INFORMATION, "Você clicou no botão solicitar emprestimo!", ButtonType.OK);
		alert.setTitle("Atenção!");
		alert.setHeaderText("Informação: ");
		alert.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectCol.setCellValueFactory(new PropertyValueFactory<>("selected"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabela.setItems(listaDeClientes());

	}
	
    private ObservableList<EmprestimoModel> listaDeClientes() {
        return FXCollections.observableArrayList(
                new EmprestimoModel("Antonio", 28, "Rua Alvenaria 22"),
                new EmprestimoModel("Bruno", 19, "Rua São Domingos 108"),
                new EmprestimoModel("Manoel", 45, "Rua Valentim 05"),
                new EmprestimoModel("Cassandra", 33, "Rua Palmeira 234"),
                new EmprestimoModel("Roberto", 69, "Rua Jean Nassif 56"),
                new EmprestimoModel("Mariana", 16, "Av Rendeiras 78")
        );
    }

}
