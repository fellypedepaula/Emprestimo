package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EmprestimoController {

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
		Alert alert;
		alert = new Alert(AlertType.INFORMATION, "Você clicou no botão solicitar emprestimo!", ButtonType.OK);
		alert.setTitle("Atenção!");
		alert.setHeaderText("Informação: ");
		alert.show();
	}

}
