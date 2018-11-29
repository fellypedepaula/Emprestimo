package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Emprestimo;
import model.EmprestimoNegocio;
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
	private DatePicker txtDate;

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

	@FXML
	void onExcluir(ActionEvent event) throws SQLException {
		EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();
		emprestimoNegocio.deletaCliente(tabela.getSelectionModel().getSelectedItem().getId());
		listarClientes();

	}

	@FXML
	void onInserirNovo(ActionEvent event) {

	}

	@FXML
	void onSalvar(ActionEvent event) {

	}

	@FXML
	public void clickItem(MouseEvent event) {
		System.out.println("evento" + event.getClickCount());
		if (event.getClickCount() == 1) // Checking double click
		{

			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setCpf(tabela.getSelectionModel().getSelectedItem().getCpf());
			emprestimo.setNome(tabela.getSelectionModel().getSelectedItem().getNome());
			emprestimo.setEmail(tabela.getSelectionModel().getSelectedItem().getEmail());
			emprestimo.setDataNascimento(tabela.getSelectionModel().getSelectedItem().getDataNascimento());
			emprestimo.setSalario(tabela.getSelectionModel().getSelectedItem().getSalario());
			emprestimo.setTelefone(tabela.getSelectionModel().getSelectedItem().getTelefone());
			emprestimo.setValor(tabela.getSelectionModel().getSelectedItem().getValor());
			emprestimo.setDiasAtraso(tabela.getSelectionModel().getSelectedItem().getDiasAtraso());

			preencherFormulario(emprestimo);
		}
	}

//	
	ObservableList<Emprestimo> oblist = FXCollections.observableArrayList();

	@FXML
	void onPesquisar(KeyEvent event) {
		try {
			listarClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			listarClientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listarClientes() throws SQLException {
		tabela.getItems().clear();
		String condicao = pesquisa.getText().toString();
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();

		arrayList = emprestimoNegocio.buscarClientes(condicao);

		for (int i = 0; i < arrayList.size(); i++) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(arrayList.get(i).getId());
			emprestimo.setNome(arrayList.get(i).getNome());
			emprestimo.setCpf(arrayList.get(i).getCpf());
			emprestimo.setValor(arrayList.get(i).getValor());
			oblist.add(emprestimo);
			tabela.setItems(oblist);
		}
		selectCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		valorDivida.setCellValueFactory(new PropertyValueFactory<>("valor"));
		nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	public void preencherFormulario(Emprestimo emprestimo) {
		txtNome.setText(emprestimo.getNome());
		txtEmail.setText(emprestimo.getEmail());
		txtCpf.setText(emprestimo.getCpf());
		txtDate.setAccessibleText(emprestimo.getDataNascimento());
		txtSalario.setText(toString(emprestimo.getSalario()));
		txtTelefone.setText(emprestimo.getTelefone());
		txtValor.setText(emprestimo.getValor());
		txtAtraso.setText(toString(emprestimo.getDiasAtraso()));
	}

	private String toString(float salario) {
		// TODO Auto-generated method stub
		return null;
	}

}
