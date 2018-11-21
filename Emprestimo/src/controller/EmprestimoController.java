package controller;

import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.UsuarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import model.Emprestimo;
import model.UsuarioModel;
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
	private TableView<Emprestimo> tabela;

	@FXML
	private TableColumn<Emprestimo, Boolean> selectCol;

	@FXML
	private TableColumn<Emprestimo, String> nomeCol;

	@FXML
	private TableColumn<Emprestimo, String> colunaCpf;

	@FXML
	private TableColumn<Emprestimo, String> valorDivida;

	@FXML
	private TextField txtAtraso;

	@FXML
	private TextField pesquisa;

//	@FXML
//	void onSolicitarProposta(ActionEvent event) {
//
//		System.out.println("passou aqui");
//
//		Connection conn;
//		conn = emDao.abreConexaoBD();
//
//		System.out.println("passou aqui 02");
//
//		Alert alert;
//		alert = new Alert(AlertType.INFORMATION, "Você clicou no botão solicitar emprestimo!", ButtonType.OK);
//		alert.setTitle("Atenção!");
//		alert.setHeaderText("Informação: ");
//		alert.show();
//	}
//	
	ObservableList<Emprestimo> oblist = FXCollections.observableArrayList();

	@FXML
	void onPesquisar(KeyEvent event) {
		Connection conn;
		conn = emDao.abreConexaoBD();

		try {
			tabela.getItems().clear();
			String query, condicao = pesquisa.getText().toString();
			query = "SELECT * FROM EMPRESTIMO WHERE  (NOME LIKE '%" + condicao + "%' OR CPF LIKE '%" + condicao + "%')";
			System.out.println("tentando executar" + query);

			ResultSet rs = conn.createStatement().executeQuery(query);

			while (rs.next()) {
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setNome(rs.getString("NOME"));
				emprestimo.setCpf(rs.getString("CPF"));
				emprestimo.setValor(rs.getString("SALARIO"));
				oblist.add(emprestimo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectCol.setCellValueFactory(new PropertyValueFactory<>("selected"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

		selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

		nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());

		tabela.setItems(oblist);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			listarClientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(" lista recebida " + arrayListEmp);

//		Connection conn;
//		conn = emDao.abreConexaoBD();
//
//		try {
//
//			String query, condicao = pesquisa.getText().toString();
//			query = "SELECT * FROM EMPRESTIMO WHERE  (NOME LIKE '%" + condicao + "%' AND CPF LIKE '%" + condicao
//					+ "%')";
//			System.out.println("tentando executar" + query);
//
//			ResultSet rs = conn.createStatement().executeQuery(query);
//
//			while (rs.next()) {
//				Emprestimo emprestimo = new Emprestimo();
//				emprestimo.setNome(rs.getString("NOME"));
//				emprestimo.setCpf(rs.getString("CPF"));
//				emprestimo.setValor(rs.getFloat("SALARIO"));
//				oblist.add(emprestimo);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		selectCol.setCellValueFactory(new PropertyValueFactory<>("selected"));
//		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
//		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
//		valorDivida.setCellValueFactory(new PropertyValueFactory<>("salario"));
//
//		selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
//		nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());
//
//		tabela.setItems(oblist);

	}

	public void listarClientes() throws SQLException {
		String condicao = pesquisa.getText().toString();

		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		try {
			Emprestimo emprestimo = new Emprestimo();
			System.out.println("inicio" + arrayList);

			arrayList = emprestimo.buscaClientes(condicao);

			for (int i = 0; i < arrayList.size(); i++) {
				Emprestimo emprestimo2 = new Emprestimo();
				emprestimo2.setId(arrayList.get(i).getId());
				emprestimo2.setNome(arrayList.get(i).getNome());
				emprestimo2.setCpf(arrayList.get(i).getCpf());
				emprestimo2.setValor(arrayList.get(i).getValor());
				System.out.println("lista de nomes " + arrayList.get(i).getNome() + "\n" + "lista de ids "
						+ arrayList.get(i).getId());
				oblist.add(emprestimo2);
				tabela.setItems(oblist);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		valorDivida.setCellValueFactory(new PropertyValueFactory<>("valor"));

//		selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
		nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());

	}

}
