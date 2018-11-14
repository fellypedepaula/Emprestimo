package controller;

import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import persistence.EmprestimoDAO;

public class EmprestimoController {
	
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
	void onSolicitarProposta(ActionEvent event) {
		
		System.out.println("passou aqui");
		
		Connection conn;
		conn = emDao.abreConexaoBD();
		
		System.out.println("passou aqui 02");

		Alert alert;
		alert = new Alert(AlertType.INFORMATION, "Voc� clicou no bot�o solicitar emprestimo!", ButtonType.OK);
		alert.setTitle("Aten��o!");
		alert.setHeaderText("Informa��o: ");
		alert.show();
	}

}
