package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.util.Util;

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
import util.TextFieldFormatter;

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
    private TextField txtIdForm;

	@FXML
	private TextField pesquisa;

	@FXML
	void onExcluir(ActionEvent event) throws SQLException {
		EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();
		emprestimoNegocio.deletaCliente(tabela.getSelectionModel().getSelectedItem().getId());
		limparFormulario();
		listarClientes();

	}

	@FXML
	void onInserirNovo(ActionEvent event) {
		limparFormulario();
		txtNome.requestFocus();

	}

	@FXML
	void onSalvar(ActionEvent event) throws SQLException, ParseException {

		EmprestimoNegocio emprestimoNegocio = new EmprestimoNegocio();
		Emprestimo emprestimo = new Emprestimo();
		ArrayList<Emprestimo> arrayList = new ArrayList<Emprestimo>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date data = new java.sql.Date(format.parse(txtDate.getPromptText()).getTime());
		
		System.out.println("Conversão da data" + data);

		
		emprestimo.setCpf(txtCpf.getText());
		emprestimo.setNome(txtNome.getText());
		emprestimo.setEmail(txtEmail.getText());
		emprestimo.setDataNascimento(data);
		emprestimo.setSalario(Float.valueOf(txtSalario.getText()));
		emprestimo.setTelefone(txtTelefone.getText());
		emprestimo.setValor(Float.valueOf(txtValor.getText()));
		emprestimo.setSexo("M");
		emprestimo.setDiasAtraso(Integer.valueOf(txtAtraso.getText()));
		emprestimo.setId(Integer.valueOf(txtIdForm.getText()));
		
		arrayList = emprestimoNegocio.buscarClientePorCpf(emprestimo);

		
		if(arrayList.isEmpty()) {
			emprestimoNegocio.inserirCliente(emprestimo);
		}else {
			emprestimo.setId(1);
			emprestimoNegocio.editarCliente(emprestimo);
		}
		
		listarClientes();
	}

	@FXML
	public void clickItem(MouseEvent event) {

		if (event.getClickCount() == 1) // Checking double click
		{

			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(tabela.getSelectionModel().getSelectedItem().getId());
			emprestimo.setCpf(tabela.getSelectionModel().getSelectedItem().getCpf());
			emprestimo.setNome(tabela.getSelectionModel().getSelectedItem().getNome());
			emprestimo.setEmail(tabela.getSelectionModel().getSelectedItem().getEmail());
			emprestimo.setSalario(tabela.getSelectionModel().getSelectedItem().getSalario());
			emprestimo.setTelefone(tabela.getSelectionModel().getSelectedItem().getTelefone());
			emprestimo.setValor(tabela.getSelectionModel().getSelectedItem().getValor());
			emprestimo.setDiasAtraso(tabela.getSelectionModel().getSelectedItem().getDiasAtraso());
			emprestimo.setSexo(tabela.getSelectionModel().getSelectedItem().getSexo());
			emprestimo.setDataNascimento(tabela.getSelectionModel().getSelectedItem().getDataNascimento());
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

//		txtDate.setValue(LocalDate.now());

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
			emprestimo.setEmail(arrayList.get(i).getEmail());
			emprestimo.setTelefone(arrayList.get(i).getTelefone());
			emprestimo.setSalario(arrayList.get(i).getSalario());
			emprestimo.setSexo(arrayList.get(i).getSexo());
			emprestimo.setDiasAtraso(arrayList.get(i).getDiasAtraso());
			emprestimo.setDataNascimento(arrayList.get(i).getDataNascimento());
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

		txtIdForm.setText(String.valueOf(emprestimo.getId()));
		txtNome.setText(emprestimo.getNome());
		txtEmail.setText(emprestimo.getEmail());
		txtCpf.setText(emprestimo.getCpf());
		txtDate.setPromptText(String.valueOf(emprestimo.getDataNascimento()));
		txtSalario.setText(String.valueOf(emprestimo.getSalario()));
		txtTelefone.setText(emprestimo.getTelefone());
		txtValor.setText(String.valueOf(emprestimo.getValor()));
		txtAtraso.setText(String.valueOf(emprestimo.getDiasAtraso()));
		verificaSexo(emprestimo.getSexo().toString());
		
		
		utilitarios.Util tff = new utilitarios.Util();
    	tff.setMask("(##)#####-####");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtTelefone);
    	tff.formatter();
		
	}

	public void limparFormulario() {
		txtNome.setText("");
		txtEmail.setText("");
		txtCpf.setText("");
		txtDate.setAccessibleText("");
		txtSalario.setText("");
		txtTelefone.setText("");
		txtValor.setText("");
		txtAtraso.setText("");
		txtIdForm.setText("");

	}

	public void verificaSexo(String sexo) {

		if (sexo.equals("M")) {
			isHomem.setSelected(true);
			isMulher.setSelected(false);

		} else {
			isMulher.setSelected(true);
			isHomem.setSelected(false);
		}
	}

}
